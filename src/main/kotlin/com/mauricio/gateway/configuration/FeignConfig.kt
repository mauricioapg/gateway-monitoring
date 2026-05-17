package com.mauricio.gateway.configuration

import feign.Logger
import feign.RequestInterceptor
import feign.codec.ErrorDecoder
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class FeignConfig {

    private val log = LoggerFactory.getLogger(FeignConfig::class.java)

    @Bean
    fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()
    }

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template ->

            template.header(
                "x-api-key",
                "zurgD6SwnioG1bcAHqnjBPdBSWDjw2GuzNQNzgaEMo82HJRseynjyFwJ9tDcXIyG"
            )
        }
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return ErrorDecoder.Default()
    }
}