package com.example.testusersapp.data.model

import androidx.room.ColumnInfo

data class FriendEntity(@ColumnInfo(name = "friend")
                        var friend: List<String> = ArrayList())