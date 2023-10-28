package com.kopring.ex.sewonyun.certificate

import com.auth0.jwt.JWT.decode
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class UpbitJwtBuilderTest {

    val testAccessValue = "test_access_key"
    val testSecretValue = "test_secret_key"

    @Autowired
    lateinit var upbitJwtBuilder: UpbitJwtBuilder

    @BeforeEach
    fun setup() {
        upbitJwtBuilder.accessKey = testAccessValue
        upbitJwtBuilder.secretKey = testSecretValue
    }

    @Test
    fun testBuildJwt() {
        upbitJwtBuilder.setParameters(
            hashMapOf(
                "param1" to "value1",
                "param2" to "value2"
            )
        )

        val jwtString = upbitJwtBuilder.build()
        val decoded = decode(jwtString.value.split(" ").last())
        val decodedClaim = String(Base64.getUrlDecoder().decode(decoded.payload))
        val claimObj: Claim = Json.decodeFromString<Claim>(decodedClaim)

        assertEquals(testAccessValue, claimObj.access_key)
    }

    @Test
    fun testSetParameters() {

        val params = hashMapOf(
            "param1" to "value1",
            "param2" to "value2"
        )

        upbitJwtBuilder.setParameters(params)
        assert(upbitJwtBuilder.queryHash.isNotBlank())
    }
}
