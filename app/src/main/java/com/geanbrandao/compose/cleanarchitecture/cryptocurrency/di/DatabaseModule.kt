package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.di

import android.content.Context
import androidx.room.Room
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.AppDatabase
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.AppDatabase.Companion.DB_NAME
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCoinDao(appDatabase: AppDatabase): CoinDao {
        return appDatabase.coinDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME,
        ).build()
    }
}
