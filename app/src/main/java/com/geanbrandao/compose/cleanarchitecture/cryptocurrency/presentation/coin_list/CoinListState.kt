package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_list

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
