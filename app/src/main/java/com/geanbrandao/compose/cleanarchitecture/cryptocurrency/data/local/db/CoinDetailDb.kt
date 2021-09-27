package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.TeamMemberDto
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.CoinDetail
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.TeamMember

@Entity
data class CoinDetailDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMemberDto>,
    val description: String,
)

fun CoinDetailDb.toCoinDetail() = CoinDetail(
    coinId = id,
    name = name,
    description = description,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
    tags = tags,
    team = team.map { TeamMember(name = it.name, position = it.position) }
)