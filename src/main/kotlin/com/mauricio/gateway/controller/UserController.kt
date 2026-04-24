package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val monitoringAPI: MonitoringAPI
) {

    @GetMapping
    fun list(): ResponseEntity<*> {
        return monitoringAPI.getUsers()
    }
}
