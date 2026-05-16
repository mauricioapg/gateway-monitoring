package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import com.mauricio.gateway.adapter.monitoring.request.UserRequestDTO
import com.mauricio.gateway.adapter.monitoring.response.PageResponseDTO
import com.mauricio.gateway.adapter.monitoring.response.UserResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val monitoringAPI: MonitoringAPI
) {

    @GetMapping("/test-monitoring")
    fun testMonitoring(): ResponseEntity<String> {

        val url = "https://monitoring-api-1.onrender.com/health"

        val response = java.net.URL(url)
            .readText()

        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun list(): PageResponseDTO<UserResponseDTO> {
        return monitoringAPI.getUsers(0, 20)
    }

    @GetMapping("/teste")
    fun listTeste(): List<UserResponseDTO> {
        return monitoringAPI.getUsersTeste()
    }

    @PostMapping
    fun create(
        @RequestBody request: UserRequestDTO
    ): UserResponseDTO =
        monitoringAPI.createUser(request)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): UserResponseDTO =
        monitoringAPI.getUserById(id)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UserRequestDTO
    ): UserResponseDTO =
        monitoringAPI.updateUser(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) =
        monitoringAPI.deleteUser(id)
}
