package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.repository

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.CoinPaprikaApi
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDetailDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId = coinId)
    }
}