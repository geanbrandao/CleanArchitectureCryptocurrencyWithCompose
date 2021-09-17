package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.get_coin

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.toCoin
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.toCoinDetails
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.Coin
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId = coinId).toCoinDetails()
            emit(Resource.Success(data = coin))
        } catch (e: HttpException) { // get exception when code response dosen't start with 2xx
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) { // get exception when our use case can't connect with the API
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection"))
        }
    }
}