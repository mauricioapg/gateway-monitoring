package com.mauricio.gateway.adapter.monitoring.request

data class UserRequestDTO(
    val documentNumber: String,
    val clients: MutableList<String>
)