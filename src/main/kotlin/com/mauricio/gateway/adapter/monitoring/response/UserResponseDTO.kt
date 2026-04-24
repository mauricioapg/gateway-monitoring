package com.mauricio.gateway.adapter.monitoring.response

import java.time.LocalDateTime
import java.util.UUID

data class UserResponseDTO(
    val id: UUID,
    val alias: String,
    val email: String,
    val password: String? = null,
    val createdAt: String
)