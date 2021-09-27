package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db.CoinDetailDb
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.TeamMemberDto

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)

fun CoinDetail.toCoinDetailDb() = CoinDetailDb(
    id = coinId,
    name = name,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
    tags = tags,
    team = team.map {
        TeamMemberDto(
            id = "",
            name = it.name,
            position = it.position
        )
    },
    description = description,
)

fun CoinDetail.toCoin() = Coin(
    id = coinId,
    name = name,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
)
