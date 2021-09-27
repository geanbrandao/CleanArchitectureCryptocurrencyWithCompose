package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_saved

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.common.Resource
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.use_case.get_coins_db.GetCoinsDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinSavedViewModel @Inject constructor(
    private val getCoinsDbUseCase: GetCoinsDbUseCase,
): ViewModel() {

    private val _state = mutableStateOf(CoinListDbState())
    val state: State<CoinListDbState> = _state
    private var currentJob: Job? = null
//    init {
//        getCoinsDb()
//    }

    fun getCoinsDb() {
        currentJob = getCoinsDbUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CoinListDbState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListDbState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListDbState(coins = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun cancelTheJob() {
        currentJob?.cancel()
    }
}