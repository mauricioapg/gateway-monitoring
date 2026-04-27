package com.mauricio.gateway.adapter.monitoring.request

import com.mauricio.apimonitoring.enum.StatusApiEnum

data class ApiCheckHistoryRequestDTO(
    val apiId: String,
    val responseTimeMs: Int,
    val status: StatusApiEnum,
    val message: String? = null,
    val details: String? = null
)