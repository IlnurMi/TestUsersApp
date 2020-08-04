package com.example.testusersapp.data.interfaces

import com.example.testusersapp.data.model.User
import io.reactivex.Single

interface AppRepositoryInterface {
    fun getAllUsers(): Single<List<User>>
    fun getAllUsersWithDatabase(): Single<List<User>>
    fun getUserById(id: Int)
}