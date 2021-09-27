package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local

import androidx.room.*
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coinDetailDb: CoinDetailDb): Long

    @Delete
    suspend fun removeCoin(coinDetailDb: CoinDetailDb)

    @Query("SELECT * FROM CoinDetailDb ORDER BY rank")
    suspend fun getAllCoins(): List<CoinDetailDb>

    @Query("SELECT EXISTS (SELECT * FROM CoinDetailDb WHERE id = :id)")
    suspend fun checkCoinExistLocal(id: String): Boolean
}