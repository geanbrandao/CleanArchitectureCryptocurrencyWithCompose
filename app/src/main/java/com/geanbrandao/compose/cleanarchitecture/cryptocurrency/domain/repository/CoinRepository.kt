package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDetailDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}