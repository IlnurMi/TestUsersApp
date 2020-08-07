package com.example.testusersapp.data.database.dao

import androidx.room.*
import com.example.testusersapp.data.model.UserEntity
import io.reactivex.Single

@Dao
interface UserDao: BaseDao<UserEntity> {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users")
    fun getUsersSingle(): Single<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): Single<UserEntity>

    @Query("SELECT * FROM users WHERE id IN (:listId)")
    fun getUserFriends(listId: List<Int>): Single<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<UserEntity>)
}