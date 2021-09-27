package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDetailDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Here we put the fuction and routes
 */
interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}