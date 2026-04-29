package com.mauricio.gateway.configuration

import feign.RequestInterceptor
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->

            val log = LoggerFactory.getLogger("FEIGN")

            template.header("x-api-key", "zurgD6SwnioG1bcAHqnjBPdBSWDjw2GuzNQNzgaEMo82HJRseynjyFwJ9tDcXIyG")

            log.info("""
                FEIGN REQUEST
                URL: ${template.url()}
                METHOD: ${template.method()}
                HEADERS: ${template.headers()}
            """.trimIndent())
        }
    }
}