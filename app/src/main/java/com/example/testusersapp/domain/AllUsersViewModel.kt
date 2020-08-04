package com.example.testusersapp.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.repository.AppRepository

class AllUsersViewModel(private val repository: AppRepository): ViewModel() {
    private var liveData: MutableLiveData<List<User>>? = null

    fun getAllUsers(){
        repository.getAllUsers().subscribe(){it -> liveData?.value = it}
    }

    fun getLiveDataUsers(): MutableLiveData<List<User>>? {
        return liveData
    }

    override fun onCleared() {
        super.onCleared()
    }
}