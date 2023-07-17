package com.example.testusersapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testusersapp.domain.interfaces.interactors.LoginInteractor

class LoginViewModel(private val loginInteractor: LoginInteractor) : ViewModel() {
    private val codeLiveData = MutableLiveData<String>()
    private val successStateMutableLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Pair<String, Int>>()

    fun setPinCode(code: String) {
        codeLiveData.value?.let { pin ->
            when (pin.length) {
                0, 1, 2 -> {
                    codeLiveData.value = pin + code
                }
                3 -> {
                    codeLiveData.value = pin + code
                    loginInteractor.saveAndCheckPinCode(codeLiveData.value!!, { success ->
                        successStateMutableLiveData.value = success
                    }, { error ->
                        errorLiveData.value = errorLiveData.value?.let { Pair(error, it.second + 1) } ?: run { Pair(error, 0) }
                    })
                }
                else -> {}
            }
        } ?: run {
            codeLiveData.value = code
        }
    }

    fun getCodeLiveData(): MutableLiveData<String> {
        return codeLiveData
    }

    fun getErrorLiveData(): MutableLiveData<Pair<String, Int>> {
        return errorLiveData
    }

    fun getSuccessStateLiveData(): MutableLiveData<Boolean> {
        return successStateMutableLiveData
    }

    fun clearText() {
        codeLiveData.value = ""
    }

    fun clearErrorTime(){
        errorLiveData.value = Pair("", 0)
    }

    override fun onCleared() {
        super.onCleared()
    }
}