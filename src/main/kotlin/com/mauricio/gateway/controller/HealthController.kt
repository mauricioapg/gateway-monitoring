package com.mauricio.gateway.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/health")
class HealthController() {

    @GetMapping
    fun health() = "OK"
}
