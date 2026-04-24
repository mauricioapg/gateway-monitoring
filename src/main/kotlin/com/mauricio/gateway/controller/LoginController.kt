package com.mauricio.gateway.controller

import com.mauricio.gateway.adapter.monitoring.MonitoringAPI
import com.mauricio.gateway.dto.LoginRequestDTO
import com.mauricio.gateway.dto.LoginResponseDTO
import com.mauricio.gateway.service.JwtService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val monitoringAPI: MonitoringAPI,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {

        val user = monitoringAPI.getUserByEmail(request.email)

        val passwordMatches = passwordEncoder.matches(request.password, user.password)

        if (!passwordMatches) {
            throw RuntimeException("Senha inválida")
        }

        val token = jwtService.generateToken(user.email)

        return ResponseEntity.ok(LoginResponseDTO(token))
    }
}