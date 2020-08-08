package com.example.testusersapp.data.database.dao

import androidx.room.*

@Dao
interface BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item : T)

    @Update
    fun update(item: T)

    @Update
    fun delete(item: T)
}