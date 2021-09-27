package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.get_coins

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.toCoin
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.Coin
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(data = coins))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection"))
        }
    }
}