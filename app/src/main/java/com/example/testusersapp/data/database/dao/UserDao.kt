package com.example.testusersapp.data.database.dao

import androidx.room.*
import com.example.testusersapp.data.model.User
import io.reactivex.Single

@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

    @Query("SELECT * FROM users")
    fun getUsersSingle(): Single<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): Single<User>

    @Query("SELECT * FROM users WHERE id IN (:listId)")
    fun getUserFriends(listId: List<Int>): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<User>)
}