package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.di

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Constants
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.CoinPaprikaApi
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.repository.CoinRepositoryImpl
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // this means this live as long as our application
object AppModule {

    @Provides
    @Singleton // only have a single instance
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api = api)
    }
}