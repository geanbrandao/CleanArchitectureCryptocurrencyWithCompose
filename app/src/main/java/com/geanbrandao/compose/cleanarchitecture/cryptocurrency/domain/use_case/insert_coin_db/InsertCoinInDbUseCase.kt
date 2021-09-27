package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.insert_coin_db

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertCoinInDbUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinDetailDb: CoinDetailDb): Flow<Resource<Long>> = flow {
        try {
            emit(Resource.Loading<Long>())
            val id = repository.insertCoinInDb(coinDetailDb = coinDetailDb)
            emit(Resource.Success(data = id))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(Resource.Error<Long>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<Long>("Couldn't reach server. Check your internet connection"))
        }
    }
}