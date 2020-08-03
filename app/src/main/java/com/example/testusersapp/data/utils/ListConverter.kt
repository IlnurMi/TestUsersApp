package com.example.testusersapp.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter() {
    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun listFromJson(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() { }.type
        return Gson().fromJson(value, listType)
    }
}