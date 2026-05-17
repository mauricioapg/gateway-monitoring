package com.mauricio.gateway.adapter.monitoring.response

import com.mauricio.apimonitoring.enum.HttpMethodEnum
import com.mauricio.gateway.dto.HeaderEmbeddable
import com.mauricio.gateway.dto.ParamsEmbeddable
import java.time.LocalDateTime
import java.util.UUID

data class MonitoredApiResponseDTO(
    val id: UUID? = null,
    val name: String,
    val url: String,
    val method: HttpMethodEnum,
    val intervalMinutes: Int,
    val maxFailureThreshold: Int,
    val timeout: Int,
    val timeToSetOffline: Int?,
    val headers: MutableList<HeaderEmbeddable>,
    val params: MutableList<ParamsEmbeddable>,
    val responsibleEmails: MutableList<String>,
    val active: Boolean,
    val createdBy: String
)