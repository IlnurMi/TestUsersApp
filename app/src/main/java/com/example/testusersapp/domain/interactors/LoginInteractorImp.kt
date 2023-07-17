package com.example.testusersapp.domain.interactors

import com.example.testusersapp.domain.interfaces.interactors.LoginInteractor
import com.example.testusersapp.domain.interfaces.repositories.Repository

class LoginInteractorImp(private val repository: Repository) : LoginInteractor {
    override fun saveAndCheckPinCode(
        code: String,
        onSuccess: (success: Boolean) -> Unit,
        onError: (e: String) -> Unit
    ) {
        repository.saveAndCheckPinCode(code, onSuccess, onError)
    }
}