package com.mauricio.gateway.adapter.monitoring

import com.mauricio.gateway.adapter.monitoring.request.UserRequestDTO
import com.mauricio.gateway.adapter.monitoring.response.UserResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@FeignClient(
    name = "monitoring-api",
    url = "\${monitoring-api.url}"
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
    fun getUsers(): ResponseEntity<*>

    @GetMapping(
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    fun getUserById(@PathVariable id: String): ResponseEntity<*>

    @PutMapping(
        value = ["users/{id}"],
        produces = ["application/json"]
    )
    fun updateUser(@PathVariable id: UUID, @RequestBody request: UserRequestDTO): ResponseEntity<*>

    @DeleteMapping(
        value = ["users/{id}"],
        produces = ["application/json"]
    )
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<*>

    @PostMapping(
        value = ["/users"],
        produces = ["application/json"]
    )
    fun createUser(@RequestBody request: UserRequestDTO): ResponseEntity<*>

}