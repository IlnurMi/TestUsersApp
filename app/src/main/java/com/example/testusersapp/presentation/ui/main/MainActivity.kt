package com.example.testusersapp.presentation.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.repository.AppRepository

class MainActivity : AppCompatActivity() {

    private var repository: AppRepository? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = (applicationContext as App).repositoryComponent?.repository
        repository?.getAllUsers()?.subscribe({result -> success(result)}, {error -> error(error)})
    }

    fun success(result: List<User>){
        Log.d("TAG", "success: ${result.get(0)}")
    }

    fun error(error: Throwable){
        Log.d("TAG", "error: $error")
    }
}
