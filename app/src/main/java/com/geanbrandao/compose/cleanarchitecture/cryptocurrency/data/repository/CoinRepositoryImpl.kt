package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.repository

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.CoinDao
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.CoinPaprikaApi
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDetailDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.CoinDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
    private val coinDao: CoinDao,
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId = coinId)
    }

    override suspend fun insertCoinInDb(coinDetailDb: CoinDetailDb): Long {
        return coinDao.insertCoin(coinDetailDb = coinDetailDb)
    }

    override suspend fun getAllCoins(): List<CoinDetailDb> {
        return coinDao.getAllCoins()
    }

    override suspend fun removeCoinFromDb(coinDetailDb: CoinDetailDb) {
        return coinDao.removeCoin(coinDetailDb = coinDetailDb)
    }

    override suspend fun checkCoinExistLocal(coinId: String): Boolean {
        return coinDao.checkCoinExistLocal(id = coinId)
    }
}