package com.example.testusersapp.data.utils

import androidx.room.TypeConverter
import com.example.testusersapp.data.model.FriendEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FriendsConverter {
    @TypeConverter
    fun listToJson(value: List<FriendEntity>?): String = Gson().toJson(value)

    @TypeConverter
    fun listFromJson(value: String?): List<FriendEntity> {
        val listType = object : TypeToken<List<FriendEntity>>() { }.type
        return Gson().fromJson(value, listType)
    }
}