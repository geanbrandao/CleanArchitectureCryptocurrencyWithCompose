package com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.local.converters

import androidx.room.TypeConverter
import com.geanbrandao.compose.cleanarchitecture.cryptocurrency.data.remote.dto.TeamMemberDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toListOfString(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListOfString(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toListTeamMemberDto(value: String?): List<TeamMemberDto> {
        val listType = object : TypeToken<List<TeamMemberDto>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListTeamMemberDto(value: List<TeamMemberDto>?): String {
        return Gson().toJson(value)
    }
}