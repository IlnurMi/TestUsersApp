package com.example.testusersapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import com.example.testusersapp.domain.models.User

class AllUsersViewModel(private val interactor: MainInteractor) : ViewModel() {
    private val allUsersLiveData = MutableLiveData<List<User>>()
    private val userLiveData = MutableLiveData<User>()
    private val friendsLiveData = MutableLiveData<List<User>>()
    private val errorLiveData = MutableLiveData<String>()

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        interactor.getAllUsers({
            allUsersLiveData.value = it
        }, {
            errorLiveData.value = it
        })
    }

    fun getLiveDataUsers(): LiveData<List<User>>? {
        return allUsersLiveData
    }

    fun getUser(id: Int) {
        interactor.getUserById(id, {
            userLiveData.value = it
        }, {
            errorLiveData.value = it
        })
    }

    fun getUserLiveData(): LiveData<User> {
        return userLiveData
    }

    fun getUserFriends(friendsId: List<String>) {
        interactor.getUsersFriends(friendsId, {
            friendsLiveData.value = it
        }, {
            errorLiveData.value = it
        })
    }

    fun getUserFriendsLiveData(): LiveData<List<User>> {
        return friendsLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}