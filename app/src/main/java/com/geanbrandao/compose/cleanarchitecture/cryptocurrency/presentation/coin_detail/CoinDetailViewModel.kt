package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Constants
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.coin_exist_local.CoinExistLocalUseCase
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.insert_coin_db.InsertCoinInDbUseCase
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.remove_coin_db.RemoveCoinFromDbUseCase
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states.CheckCoinExistLocalState
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states.CoinDetailState
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states.InsertCoinInDbState
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states.RemoveCoinFromDbState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * [savedStateHandle] this will contain all the arguments used in navigation components,
 * when we navigate from a fragment to another
 */

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val insertCoinInDbUseCase: InsertCoinInDbUseCase,
    private val removeCoinFromDbUseCase: RemoveCoinFromDbUseCase,
    private val coinExistLocalUseCase: CoinExistLocalUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _coinDetailsState = mutableStateOf(CoinDetailState())
    val coinDetailsState: State<CoinDetailState> = _coinDetailsState

    private val _insertCoinInDbState = mutableStateOf(InsertCoinInDbState())
    val insertCoinInDbState: State<InsertCoinInDbState> = _insertCoinInDbState

    private val _removeCoinFromDbState = mutableStateOf(RemoveCoinFromDbState())
    val removeCoinFromDbState: State<RemoveCoinFromDbState> = _removeCoinFromDbState

    private val _checkCoinExistLocalState = mutableStateOf(CheckCoinExistLocalState())
    val checkCoinExistLocalState: State<CheckCoinExistLocalState> = _checkCoinExistLocalState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId = coinId)
            checkCoinExistLocal(coinId = coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId = coinId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _coinDetailsState.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _coinDetailsState.value = CoinDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _coinDetailsState.value = CoinDetailState(coin = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertCoinInDb(coinDetailDb: CoinDetailDb) {
        insertCoinInDbUseCase(coinDetailDb = coinDetailDb).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _insertCoinInDbState.value = InsertCoinInDbState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _insertCoinInDbState.value = InsertCoinInDbState(isLoading = true)
                }
                is Resource.Success -> {
                    _insertCoinInDbState.value = InsertCoinInDbState(id = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeCoinFromDb(coinDetailDb: CoinDetailDb) {
        removeCoinFromDbUseCase(coinDetailDb = coinDetailDb).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _removeCoinFromDbState.value = RemoveCoinFromDbState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _removeCoinFromDbState.value = RemoveCoinFromDbState(isLoading = true)
                }
                is Resource.Success -> {
                    _removeCoinFromDbState.value = RemoveCoinFromDbState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkCoinExistLocal(coinId: String) {
        coinExistLocalUseCase(coinId = coinId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _checkCoinExistLocalState.value = CheckCoinExistLocalState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _checkCoinExistLocalState.value = CheckCoinExistLocalState(isLoading = true)
                }
                is Resource.Success -> {
                    _checkCoinExistLocalState.value = CheckCoinExistLocalState(exists = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}