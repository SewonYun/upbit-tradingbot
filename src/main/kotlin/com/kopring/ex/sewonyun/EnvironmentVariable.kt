package com.kopring.ex.sewonyun

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:env.properties")
class EnvironmentVariable {

    @Value("\${ACCESS_KEY}")
    private lateinit var accessKey: String

    @Value("\${SECRET_KEY}")
    private lateinit var secretKey: String

    @Value("\${UMBI_URL}")
    private lateinit var ubmiUrl: String

    @Value("\${UPBIT_REPOSITORY_URI}")
    private lateinit var serverURI: String

    fun getAccessKey(): String? {
        return accessKey
    }
    fun getSecretKey(): String? {
        return secretKey
    }
    fun getServerUrl(): String? {
        return serverURI
    }

    fun getUBMIUrl(): String? {
        return ubmiUrl
    }


}

