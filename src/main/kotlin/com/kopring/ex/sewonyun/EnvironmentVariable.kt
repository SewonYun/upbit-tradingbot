package com.kopring.ex.sewonyun

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:env.properties")
class EnvironmentVariable {

    @Value("\${ACCESS_KEY}")
    private val accessKey: String? = null

    fun getAccessKey(): String? {
        return accessKey
    }

}

