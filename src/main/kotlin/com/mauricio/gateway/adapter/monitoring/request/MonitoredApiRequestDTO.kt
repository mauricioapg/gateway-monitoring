package com.mauricio.gateway.adapter.monitoring.request

import com.mauricio.apimonitoring.enum.HttpMethodEnum
import com.mauricio.gateway.dto.HeaderEmbeddable
import com.mauricio.gateway.dto.ParamsEmbeddable

data class MonitoredApiRequestDTO(
    val name: String,
    val url: String,
    val method: HttpMethodEnum,
    val headers: MutableList<HeaderEmbeddable> = mutableListOf(),
    val params: MutableList<ParamsEmbeddable> = mutableListOf(),
    val responsibleEmails: MutableList<String> = mutableListOf(),
    val timeoutMs: Int,
    val intervalMinutes: Int,
    val maxFailureThreshold: Int,
    val timeToSetOffline: Int? = 3,
    val expectedStatus: Int = 200,
    val active: Boolean = true,
)