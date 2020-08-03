package com.example.testusersapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testusersapp.data.utils.FriendsConverter
import com.example.testusersapp.data.utils.ListConverter

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey()
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
    @Embedded(prefix = "tags_")
    @TypeConverters(ListConverter::class)
    var tags: TagsEntity,
    @Embedded(prefix = "friends_")
    @TypeConverters(FriendsConverter::class)
    val friends: FriendEntity? = null,
    val favoriteFruit: String
)