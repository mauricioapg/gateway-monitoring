package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import com.mauricio.gateway.adapter.monitoring.request.ApiCheckHistoryRequestDTO
import com.mauricio.gateway.adapter.monitoring.response.ApiCheckHistoryResponseDTO
import com.mauricio.gateway.adapter.monitoring.response.PageResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
    @RequestMapping("/api/v1/check-history")
class ApiCheckHistoryController(
    private val monitoringAPI: MonitoringAPI
) {

    @PostMapping
    fun create(
        @RequestBody request: ApiCheckHistoryRequestDTO
    ) = monitoringAPI.createApiCheckHistory(request)

    @GetMapping
    fun list(): PageResponseDTO<ApiCheckHistoryResponseDTO> {
        return monitoringAPI.getApiCheckHistory()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ApiCheckHistoryResponseDTO =
        monitoringAPI.getApiCheckHistoryById(id)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: ApiCheckHistoryRequestDTO
    ): ApiCheckHistoryResponseDTO =
        monitoringAPI.updateApiCheckHistory(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) =
        monitoringAPI.deleteApiCheckHistory(id)
}
