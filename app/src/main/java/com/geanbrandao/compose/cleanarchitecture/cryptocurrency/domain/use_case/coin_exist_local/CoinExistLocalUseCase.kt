package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.coin_exist_local

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.toCoinDetails
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinExistLocalUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val exist = repository.checkCoinExistLocal(coinId = coinId)
            emit(Resource.Success(data = exist))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<Boolean>("Couldn't reach server. Check your internet connection"))
        }
    }
}