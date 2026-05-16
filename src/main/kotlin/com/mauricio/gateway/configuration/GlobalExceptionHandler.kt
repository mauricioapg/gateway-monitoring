package com.mauricio.gateway.configuration

import feign.FeignException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<String> {

        log.error("""
            ERRO GLOBAL
            ENDPOINT: ${request.requestURI}
            METODO: ${request.method}
            ERRO: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno: ${ex.message}")
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(
        ex: FeignException,
        request: HttpServletRequest
    ): ResponseEntity<String> {

        val responseBody = try {
            ex.contentUTF8()
        } catch (e: Exception) {
            "Sem body"
        }

        log.error("""
            FEIGN ERROR
            ENDPOINT: ${request.requestURI}
            METODO: ${request.method}
            STATUS: ${ex.status()}
            RESPONSE: $responseBody
            MESSAGE: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .body("Erro ao comunicar com monitoring-api")
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<String> {

        log.error("""
            403 - ACCESS DENIED
            ENDPOINT: ${request.requestURI}
            MOTIVO: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body("Acesso negado")
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuth(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<String> {

        log.error("""
            401 - AUTH ERROR
            ENDPOINT: ${request.requestURI}
            MOTIVO: ${ex.message}
        """.trimIndent(), ex)

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body("Não autenticado")
    }
}