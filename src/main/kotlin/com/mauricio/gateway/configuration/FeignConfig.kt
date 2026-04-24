package com.mauricio.gateway.configuration

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Value("\${monitoring-api.security.key}")
    private lateinit var apiKey: String

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor {
            it.header("x-api-key", apiKey)
        }
    }
}