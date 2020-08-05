package com.example.testusersapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testusersapp.data.repository.AppRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: AppRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllUsersViewModel::class.java))
            return AllUsersViewModel(repository) as T
        throw  IllegalArgumentException("Unknown class")
    }
}