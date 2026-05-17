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

    @GetMapping
    fun list(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): PageResponseDTO<UserResponseDTO> {
        return monitoringAPI.getUsers(page, size)
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
