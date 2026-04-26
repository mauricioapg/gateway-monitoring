package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import com.mauricio.gateway.adapter.monitoring.request.UserRequestDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val monitoringAPI: MonitoringAPI
) {

    @GetMapping
    fun list(): ResponseEntity<*> {
        return monitoringAPI.getUsers()
    }

    @PostMapping
    fun create(
        @RequestBody request: UserRequestDTO
    ): ResponseEntity<*> =
        monitoringAPI.createUser(request)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<*> =
        monitoringAPI.getUserById(id)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UserRequestDTO
    ): ResponseEntity<*> =
        monitoringAPI.updateUser(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) =
        monitoringAPI.deleteUser(id)
}
