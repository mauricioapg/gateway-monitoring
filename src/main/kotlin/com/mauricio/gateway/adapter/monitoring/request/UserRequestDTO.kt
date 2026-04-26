package com.mauricio.gateway.adapter.monitoring.request

data class UserRequestDTO(
    val alias: String,
    val email: String,
    val password: String? = null,
)