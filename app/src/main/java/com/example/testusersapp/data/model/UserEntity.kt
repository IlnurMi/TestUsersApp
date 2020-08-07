package com.example.testusersapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testusersapp.data.utils.ListConverter

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val guid: String,
    val isActive: Boolean,
    val balance: String,
    val age: Int,
    val eyeColor: String,
    val name: String,
    val gender: String,
    val company: String,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    @TypeConverters(ListConverter::class)
    val tags: List<String>,
    @TypeConverters(ListConverter::class)
    val friends: List<String>,
    val favoriteFruit: String
)