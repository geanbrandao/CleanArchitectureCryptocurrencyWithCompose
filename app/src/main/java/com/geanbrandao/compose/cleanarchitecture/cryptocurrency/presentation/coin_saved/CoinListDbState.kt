package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_saved

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail

data class CoinListDbState(
    val isLoading: Boolean = false,
    val coins: List<CoinDetail> = emptyList(),
    val error: String = ""
)
