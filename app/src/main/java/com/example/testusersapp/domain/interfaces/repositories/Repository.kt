package com.example.testusersapp.domain.interfaces.repositories

import com.example.testusersapp.domain.models.User

interface Repository {
    fun getAllUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
    fun getUserById(id: Int, onSuccess: (User) -> Unit, onError: (e: String) -> Unit)
    fun getUserFriends(friendsId: List<String>, onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
    fun updateUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
}