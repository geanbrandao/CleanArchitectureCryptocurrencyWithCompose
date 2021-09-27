package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.remove_coin_db

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoveCoinFromDbUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinDetailDb: CoinDetailDb): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            repository.removeCoinFromDb(coinDetailDb = coinDetailDb)
            emit(Resource.Success(data = true))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<Boolean>("Couldn't reach server. Check your internet connection"))
        }
    }
}