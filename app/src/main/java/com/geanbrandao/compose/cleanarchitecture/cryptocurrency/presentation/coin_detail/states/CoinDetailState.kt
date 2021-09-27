package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)
