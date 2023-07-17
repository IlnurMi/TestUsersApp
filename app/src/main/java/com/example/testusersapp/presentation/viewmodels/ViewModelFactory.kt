package com.example.testusersapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testusersapp.domain.interfaces.interactors.LoginInteractor
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val interactor: MainInteractor,
                                           private val loginInteractor: LoginInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllUsersViewModel::class.java))
            return AllUsersViewModel(interactor) as T
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(loginInteractor) as T
        throw  IllegalArgumentException("Unknown class")
    }
}