package com.example.testusersapp.domain.interfaces.interactors

interface LoginInteractor {
    fun saveAndCheckPinCode(code: String, onSuccess: (success: Boolean) -> Unit, onError: (e: String) -> Unit)
}