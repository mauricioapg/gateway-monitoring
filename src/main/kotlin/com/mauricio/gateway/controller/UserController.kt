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

    @GetMapping("/test-monitoring")
    fun testMonitoring(): ResponseEntity<String> {

        val url = "https://monitoring-api-1.onrender.com/health"

        val response = java.net.URL(url)
            .readText()

        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun list(): ResponseEntity<*> {
        return monitoringAPI.getUsers()
    }

    @GetMapping("/teste")
    fun listTeste(): ResponseEntity<*> {
        return monitoringAPI.getUsersTeste()
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
