package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDetailDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun insertCoinInDb(coinDetailDb: CoinDetailDb): Long

    suspend fun getAllCoins(): List<CoinDetailDb>

    suspend fun removeCoinFromDb(coinDetailDb: CoinDetailDb)

    suspend fun checkCoinExistLocal(coinId: String): Boolean
}