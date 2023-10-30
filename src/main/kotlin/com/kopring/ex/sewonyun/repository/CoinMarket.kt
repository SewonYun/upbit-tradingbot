package com.kopring.ex.sewonyun.repository

import com.kopring.ex.sewonyun.util.HttpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CoinMarket {

    @Autowired
    lateinit var http: HttpService

    fun candles(
        market: String,
        count: Int = 1,
        unit: Int = 1,
        timeType: String = "minutes"
    ): WebClient.RequestHeadersUriSpec<*> {

        val map = HashMap<String, String>()
        map["market"] = market
        map["count"] = count.toString()

        return http.get("/v1/candles/${timeType}/${unit}", map)
    }

    fun ticks(market: String, count: Int = 1): WebClient.RequestHeadersUriSpec<*> {
        val map = HashMap<String, String>()
        map["market"] = market
        map["count"] = count.toString()

        return http.get("/v1/trades/ticks", map)
    }

    fun ticker(market: String): WebClient.RequestHeadersUriSpec<*> {
        val map = HashMap<String, String>()
        map["market"] = market
        return http.get("/v1/ticker", map)
    }

    fun orderBook(market: String, count: Int = 1): WebClient.RequestHeadersUriSpec<*> {
        val map = HashMap<String, String>()
        map["markets"] = "[${market}]"
        return http.get("/v1/orderbook", map)
    }


}