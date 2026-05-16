package com.mauricio.gateway.configuration

import feign.Logger
import feign.RequestInterceptor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig(
    @Value("\${monitoring-api.url}")
    private val monitoringApiUrl: String
) {

    private val log = LoggerFactory.getLogger(FeignConfig::class.java)

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->

            template.header(
                "x-api-key",
                "zurgD6SwnioG1bcAHqnjBPdBSWDjw2GuzNQNzgaEMo82HJRseynjyFwJ9tDcXIyG"
            )

            log.info("""
                FEIGN REQUEST
                FULL URL: $monitoringApiUrl${template.url()}
                METHOD: ${template.method()}
                HEADERS: ${template.headers()}
            """.trimIndent())
        }
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}