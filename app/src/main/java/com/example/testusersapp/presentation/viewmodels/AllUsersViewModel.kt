package com.example.testusersapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import com.example.testusersapp.domain.models.User
import java.text.SimpleDateFormat
import java.util.*

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

    fun updateUsers(){
        interactor.updateUsers({
            allUsersLiveData.value = it
        },{
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

    fun convertDate(date: String): String {
        val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss")
        val result = dateFormat.parse(date)
        return "${changeTime(getDate(result, Calendar.HOUR))}:${changeTime(
            getDate(
                result,
                Calendar.MINUTE
            )
        )} ${changeTime(
            getDate(result, Calendar.DAY_OF_MONTH)
        )}.${changeTime(
            getDate(result, Calendar.MONTH)
        )}.${changeYear(getDate(result, Calendar.YEAR))}"
    }

    private fun changeTime(value: Int): String {
        if (value < 10)
            return "0$value"
        return "$value"
    }

    private fun changeYear(date: Int): Int{
        return date % 100
    }
    private fun getDate(date: Date, field: Int): Int {
        val calendar: Calendar = GregorianCalendar()
        calendar.time = date
        return calendar.get(field)
    }

    override fun onCleared() {
        super.onCleared()
    }
}