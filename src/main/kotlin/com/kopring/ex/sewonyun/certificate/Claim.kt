package com.kopring.ex.sewonyun.certificate

import kotlinx.serialization.Serializable

@Serializable
data class Claim(
    val access_key: String,
    val nonce: String,
    val query_hash: String,
    val query_hash_alg: String
)