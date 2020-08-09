package com.example.testusersapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testusersapp.data.database.dao.UserDao
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.utils.ListConverter

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}