package com.example.testusersapp.data.model

import androidx.room.ColumnInfo

data class TagsEntity(@ColumnInfo(name = "tag")
                 var tag: List<String> = ArrayList())