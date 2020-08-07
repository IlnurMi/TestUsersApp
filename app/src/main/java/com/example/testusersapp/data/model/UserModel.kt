package com.example.testusersapp.data.model

import com.google.gson.annotations.SerializedName

class UserModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("guid")
    val guid: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("about")
    val about: String,
    @SerializedName("registered")
    val registered: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("friends")
    val friends: List<FriendModel>? = null,
    @SerializedName("favoriteFruit")
    val favoriteFruit: String
)