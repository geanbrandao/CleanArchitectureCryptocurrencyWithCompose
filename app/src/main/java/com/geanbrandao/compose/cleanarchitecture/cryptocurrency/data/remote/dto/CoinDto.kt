package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto


import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val type: String
)

fun CoinDto.toCoin() = Coin(
    id = id,
    isActive = isActive,
    name = name,
    rank = rank,
    symbol = symbol,
)