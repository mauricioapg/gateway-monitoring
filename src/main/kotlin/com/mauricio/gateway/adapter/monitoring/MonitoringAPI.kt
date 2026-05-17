package com.mauricio.gateway.adapter.monitoring

import com.mauricio.gateway.adapter.monitoring.request.ApiCheckHistoryRequestDTO
import com.mauricio.gateway.adapter.monitoring.request.MonitoredApiRequestDTO
import com.mauricio.gateway.adapter.monitoring.request.UserRequestDTO
import com.mauricio.gateway.adapter.monitoring.response.ApiCheckHistoryResponseDTO
import com.mauricio.gateway.adapter.monitoring.response.MonitoredApiResponseDTO
import com.mauricio.gateway.adapter.monitoring.response.PageResponseDTO
import com.mauricio.gateway.adapter.monitoring.response.UserResponseDTO
import com.mauricio.gateway.configuration.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@FeignClient(
    name = "monitoring-api",
    url = "\${monitoring-api.url}",
    configuration = [FeignConfig::class]
)
interface MonitoringAPI {

    @GetMapping(
        value = ["/users/email/{email}"],
        produces = ["application/json"]
    )
    fun getUserByEmail(@PathVariable email: String?): UserResponseDTO

    @GetMapping(
        value = ["/users"],
        produces = ["application/json"]
    )
    fun getUsers(
        @RequestParam page: Int,
        @RequestParam size: Int
    ): PageResponseDTO<UserResponseDTO>

    @GetMapping(
        value = ["/users/teste"],
        produces = ["application/json"]
    )
    fun getUsersTeste(): List<UserResponseDTO>

    @GetMapping(
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    fun getUserById(@PathVariable id: String): UserResponseDTO

    @PutMapping(
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    fun updateUser(@PathVariable id: UUID, @RequestBody request: UserRequestDTO): UserResponseDTO

    @DeleteMapping(
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    fun deleteUser(@PathVariable id: UUID)

    @PostMapping(
        value = ["/users"],
        produces = ["application/json"]
    )
    fun createUser(@RequestBody request: UserRequestDTO): UserResponseDTO

    @GetMapping(
        value = ["/monitored-apis"],
        produces = ["application/json"]
    )
    fun getMonitoredApis(
        @RequestParam page: Int,
        @RequestParam size: Int
    ): PageResponseDTO<MonitoredApiResponseDTO>

    @GetMapping(
        value = ["/monitored-apis/{id}"],
        produces = ["application/json"]
    )
    fun getMonitoredApiById(@PathVariable id:  String): MonitoredApiResponseDTO

    @PostMapping(
        value = ["/monitored-apis/user/{userId}"],
        produces = ["application/json"]
    )
    fun createMonitoredApi(
        @PathVariable userId: UUID,
        @RequestBody request: MonitoredApiRequestDTO): MonitoredApiResponseDTO

    @PutMapping(
        value = ["/monitored-apis/{id}"],
        produces = ["application/json"]
    )
    fun updateMonitoredApi(
        @PathVariable id: UUID,
        @RequestBody request: MonitoredApiRequestDTO): MonitoredApiResponseDTO

    @DeleteMapping(
        value = ["/monitored-apis/{id}"],
        produces = ["application/json"]
    )
    fun deleteMonitoredApi(@PathVariable id: UUID): ApiCheckHistoryResponseDTO

    @GetMapping(
        value = ["/check-history"],
        produces = ["application/json"]
    )
    fun getApiCheckHistory(): PageResponseDTO<ApiCheckHistoryResponseDTO>

    @GetMapping(
        value = ["/check-history/{id}"],
        produces = ["application/json"]
    )
    fun getApiCheckHistoryById(@PathVariable id: String): ApiCheckHistoryResponseDTO

    @PostMapping(
        value = ["/check-history"],
        produces = ["application/json"]
    )
    fun createApiCheckHistory(@RequestBody request: ApiCheckHistoryRequestDTO): ApiCheckHistoryResponseDTO

    @PutMapping(
        value = ["/check-history/{id}"],
        produces = ["application/json"]
    )
    fun updateApiCheckHistory(
        @PathVariable id: UUID,
        @RequestBody request: ApiCheckHistoryRequestDTO): ApiCheckHistoryResponseDTO

    @DeleteMapping(
        value = ["/check-history/{id}"],
        produces = ["application/json"]
    )
    fun deleteApiCheckHistory(@PathVariable id: UUID): ApiCheckHistoryResponseDTO

}