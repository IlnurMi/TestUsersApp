package com.example.testusersapp.domain.interactors

import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import com.example.testusersapp.domain.interfaces.repositories.Repository
import com.example.testusersapp.domain.models.User

class MainInteractorImp(private val repository: Repository) : MainInteractor {

    override fun getAllUsers(onSuccess: (List<User>) -> Unit, onError: (e: String) -> Unit) {
        repository.getAllUsers(onSuccess, onError)
    }

    override fun getUserById(id: Int, onSuccess: (User) -> Unit, onError: (e: String) -> Unit) {
        repository.getUserById(id, onSuccess, onError)
    }

    override fun getUsersFriends(
        friendsId: List<String>,
        onSuccess: (List<User>) -> Unit,
        onError: (e: String) -> Unit
    ) {
        repository.getUserFriends(friendsId, onSuccess, onError)
    }

}