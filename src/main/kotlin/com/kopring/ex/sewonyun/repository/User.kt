package com.kopring.ex.sewonyun.repository

import com.kopring.ex.sewonyun.util.HttpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class User {

    @Autowired
    lateinit var http: HttpService

    fun account(): WebClient.RequestHeadersUriSpec<*> {
        return http.get("/v1/accounts", HashMap())
    }

    fun getCoins(): WebClient.RequestHeadersUriSpec<*> {
        val map = HashMap<String, String>()
        map["isDetails"] = "true"
        return http.get("/v1/market/all", map)
    }

}