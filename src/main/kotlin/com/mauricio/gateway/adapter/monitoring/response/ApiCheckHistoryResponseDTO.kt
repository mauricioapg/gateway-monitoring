package com.mauricio.gateway.adapter.monitoring.response

import com.mauricio.apimonitoring.enum.StatusApiEnum
import java.util.UUID

data class ApiCheckHistoryResponseDTO(
    val id: UUID? = null,
    val apiId: String,
    val apiName: String,
    val responseTimeMs: Int? = null,
    val status: StatusApiEnum,
    val message: String? = null,
    val details: String? = null,
    val checkedAt: String
)