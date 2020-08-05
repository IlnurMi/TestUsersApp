package com.example.testusersapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.repository.AppRepository

class AllUsersViewModel(private val repository: AppRepository): ViewModel() {
    private val liveData = MutableLiveData<List<User>>()
    private val userLiveData = MutableLiveData<User>()
    private val friendsLiveData = MutableLiveData<List<User>>()

    init {
        getAllUsers()
    }

    private fun getAllUsers(){
        repository.getAllUsersWithDatabase().subscribe(){it -> liveData?.value = it}
    }

    fun getLiveDataUsers(): LiveData<List<User>>? {
        return liveData
    }

    fun getUser(id: Int){
        repository.getUserById(id).subscribe(){it -> userLiveData.value = it}
    }

    fun getUserLiveData(): LiveData<User>{
        return userLiveData
    }

    fun getUserFriends(list: List<String>){
        repository.getUserFriends(list).subscribe(){it -> friendsLiveData.value = it}
    }

    fun getUserFriendsLiveData(): LiveData<List<User>>{
        return friendsLiveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}