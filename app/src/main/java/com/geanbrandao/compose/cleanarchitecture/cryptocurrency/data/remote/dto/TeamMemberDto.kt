package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto

import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.domain.model.TeamMember

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

fun TeamMemberDto.toTeamMember() = TeamMember(
    name = name,
    position = position,
)