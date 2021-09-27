package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states

data class InsertCoinInDbState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val error: String = "",
)