package com.mauricio.gateway.configuration

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.naming.AuthenticationException

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: org.springframework.security.core.AuthenticationException
    ) {
        log.error("401: ${authException.message}")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}