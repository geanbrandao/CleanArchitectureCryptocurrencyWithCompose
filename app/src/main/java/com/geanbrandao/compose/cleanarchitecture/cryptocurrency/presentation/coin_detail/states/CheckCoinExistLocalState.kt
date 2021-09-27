package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states

data class CheckCoinExistLocalState(
    val isLoading: Boolean = false,
    val exists: Boolean? = false,
    val error: String = "",
)
