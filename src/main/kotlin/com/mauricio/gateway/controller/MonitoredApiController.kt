package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import com.mauricio.gateway.adapter.monitoring.request.MonitoredApiRequestDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
    @RequestMapping("/api/v1/monitored-apis")
class MonitoredApiController(
    private val monitoringAPI: MonitoringAPI
) {

    @PostMapping("/user/{userId}")
    fun create(
        @PathVariable userId: UUID,
        @RequestBody request: MonitoredApiRequestDTO
    ): ResponseEntity<*> =
        monitoringAPI.createMonitoredApi(userId, request)

    @GetMapping
    fun list(): ResponseEntity<*> {
        return monitoringAPI.getMonitoredApis()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<*> =
        monitoringAPI.getMonitoredApiById(id)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: MonitoredApiRequestDTO
    ): ResponseEntity<*> =
        monitoringAPI.updateMonitoredApi(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) =
        monitoringAPI.deleteMonitoredApi(id)
}
