package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.presentation.coin_detail.states

data class RemoveCoinFromDbState(
    val isLoading: Boolean = false,
    val data: Boolean? = false,
    val error: String = ""
)
