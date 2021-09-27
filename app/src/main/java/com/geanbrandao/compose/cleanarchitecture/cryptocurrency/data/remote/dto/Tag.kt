package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto
import com.google.gson.annotations.SerializedName

data class Tag(
    val id: String,
    val name: String,
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int
)