package com.example.testusersapp.domain.interfaces.interactors

import com.example.testusersapp.domain.models.User

interface MainInteractor {
    fun getAllUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
    fun getUserById(id: Int, onSuccess: (User) -> Unit, onError: (e: String) -> Unit)
    fun getUsersFriends(friendsId: List<String>, onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
    fun updateUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit)
}