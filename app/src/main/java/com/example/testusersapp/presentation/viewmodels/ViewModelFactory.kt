package com.example.testusersapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import java.lang.IllegalArgumentException

class ViewModelFactory(private val interactor: MainInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllUsersViewModel::class.java))
            return AllUsersViewModel(interactor) as T
        throw  IllegalArgumentException("Unknown class")
    }
}