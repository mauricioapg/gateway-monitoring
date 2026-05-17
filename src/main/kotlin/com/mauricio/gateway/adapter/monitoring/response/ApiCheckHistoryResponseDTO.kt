package com.mauricio.gateway.adapter.monitoring.response

import com.mauricio.apimonitoring.enum.StatusApiEnum

data class ApiCheckHistoryResponseDTO(
    val apiId: String,
    val apiName: String,
    val responseTimeMs: Int? = null,
    val status: StatusApiEnum,
    val message: String? = null,
    val details: String? = null,
    val checkedAt: String
)