package com.kopring.ex.sewonyun.certificate

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.kopring.ex.sewonyun.EnvironmentVariable
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


@Service
class UpbitJwtBuilder{

    @Autowired
    final lateinit var environmentVariable: EnvironmentVariable

    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var queryHash: String

    @PostConstruct
    fun init() {
        accessKey = environmentVariable.getAccessKey() ?: ""
        secretKey = environmentVariable.getSecretKey() ?: ""
    }

    fun setParameters(params: HashMap<String, String>): UpbitJwtBuilder {

        val queryElements = ArrayList<String>()
        for ((key, value) in params) {
            queryElements.add("$key=$value")
        }

        val queryString = java.lang.String.join("&", *queryElements.toTypedArray<String>())

        val md = MessageDigest.getInstance("SHA-512")
        md.update(queryString.toByteArray(charset("UTF-8")))

        queryHash = String.format("%0128x", BigInteger(1, md.digest()))
        return this
    }

    fun build():JWTstring {
        val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

        val jwtToken: String = JWT.create()
            .withClaim("access_key", accessKey)
            .withClaim("nonce", UUID.randomUUID().toString())
            .withClaim("query_hash", queryHash)
            .withClaim("query_hash_alg", "SHA512")
            .sign(algorithm)

        return JWTstring("Bearer $jwtToken")
    }



}