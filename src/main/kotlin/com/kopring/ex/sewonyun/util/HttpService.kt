package com.kopring.ex.sewonyun.util

import com.kopring.ex.sewonyun.EnvironmentVariable
import com.kopring.ex.sewonyun.certificate.UpbitJwtBuilder
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.HashMap

@Service
class HttpService(private val webClientBuilder: WebClient.Builder) {

    @Autowired
    final lateinit var environmentVariable: EnvironmentVariable
    @Autowired
    final lateinit var upbitJwtBuilder: UpbitJwtBuilder


    private lateinit var serverUrl: String

    @PostConstruct
    fun init() {
        serverUrl = environmentVariable.getServerUrl()  ?: ""
    }

    /**
     * use optional chaining .retrieve() for execute http request
     */
    fun get(apiUrl: String, params: HashMap<String, String>):WebClient.RequestHeadersUriSpec<*> {
        val jwt = upbitJwtBuilder
            .setParameters(params)
            .build()

        return webClientBuilder.baseUrl(serverUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, jwt.value)
            .build()
            .get()
    }

}