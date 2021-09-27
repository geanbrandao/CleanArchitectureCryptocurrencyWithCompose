package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.AppDatabase.Companion.DB_VERSION
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.converters.Converters
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb

@Database(
    entities = [CoinDetailDb::class],
    version = DB_VERSION, exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "Cryptocurrency.db"
    }
}