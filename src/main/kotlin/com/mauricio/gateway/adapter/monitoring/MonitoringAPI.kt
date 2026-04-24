package com.mauricio.gateway.adapter.monitoring

import com.mauricio.gateway.adapter.monitoring.response.UserResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity

@FeignClient(
    name = "monitoring-api",
    url = "\${monitoring-api.url}"
)
interface MonitoringAPI {

    @GetMapping(
        value = ["/users/email/{email}"],
        produces = ["application/json"]
    )
    fun getUserByEmail(@PathVariable email: String?): UserResponseDTO

    @GetMapping(
        value = ["/users"],
        produces = ["application/json"]
    )
    fun getUsers(): ResponseEntity<*>

}