package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.Coin
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
