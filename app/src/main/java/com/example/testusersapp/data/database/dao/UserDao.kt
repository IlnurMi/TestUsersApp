package com.example.testusersapp.data.database.dao

import androidx.room.*
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.utils.ListConverter

@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<User>)
}