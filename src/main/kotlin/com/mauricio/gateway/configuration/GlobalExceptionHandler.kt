package com.mauricio.gateway.configuration

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: HttpServletRequest): ResponseEntity<String> {
        log.error("""
            ERRO GLOBAL
            Endpoint: ${request.requestURI}
            Método: ${request.method}
            Erro: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno: ${ex.message}")
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException::class)
    fun handleAccessDenied(ex: Exception, request: HttpServletRequest): ResponseEntity<String> {
        log.error("""
            403 - ACCESS DENIED
            Endpoint: ${request.requestURI}
            Motivo: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body("Acesso negado")
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException::class)
    fun handleAuth(ex: Exception, request: HttpServletRequest): ResponseEntity<String> {
        log.error("""
            401 - AUTH ERROR
            Endpoint: ${request.requestURI}
            Motivo: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Não autenticado")
    }
}