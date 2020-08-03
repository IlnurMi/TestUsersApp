package com.example.testusersapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.utils.ListConverter

@Dao
@TypeConverters(ListConverter::class)
interface UserDao: BaseDao<UserEntity> {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): UserEntity
}