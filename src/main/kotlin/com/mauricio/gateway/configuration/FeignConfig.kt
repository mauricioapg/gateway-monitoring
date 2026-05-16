package com.mauricio.gateway.configuration

import feign.Logger
import feign.RequestInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class FeignConfig(
    @Value("\${monitoring-api.security.key}")
    private val apiKey: String
) {

    private val log = LoggerFactory.getLogger(FeignConfig::class.java)

    @Bean
    fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(10, 5, TimeUnit.MINUTES))
            .retryOnConnectionFailure(true)
            .build()
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->

            template.header("x-api-key", apiKey)

            log.info("""
                FEIGN REQUEST
                FULL URL: https://monitoring-api-1.onrender.com/api/v1${template.url()}
                METHOD: ${template.method()}
                HEADERS: ${template.headers()}
            """.trimIndent())
        }
    }
}