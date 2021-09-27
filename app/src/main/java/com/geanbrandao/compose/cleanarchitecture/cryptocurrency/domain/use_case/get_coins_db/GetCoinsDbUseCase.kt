package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.get_coins_db

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.toCoinDetail
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsDbUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<CoinDetail>>> = flow {
        try {
            emit(Resource.Loading<List<CoinDetail>>())
            val coins = repository.getAllCoins().map { it.toCoinDetail() }
            emit(Resource.Success(data = coins))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(
                Resource.Error<List<CoinDetail>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<List<CoinDetail>>("Couldn't reach server. Check your internet connection"))
        }
    }
}