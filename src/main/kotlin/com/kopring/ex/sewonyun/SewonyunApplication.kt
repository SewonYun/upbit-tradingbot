package com.kopring.ex.sewonyun

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SewonyunApplication
    fun main(args: Array<String>) {
        val context = runApplication<SewonyunApplication>(*args)
        val environmentConfig = context.getBean(EnvironmentVariable::class.java)

        val accessKey = environmentConfig.getAccessKey()
        println("ACCESS_KEY: $accessKey")
    }
