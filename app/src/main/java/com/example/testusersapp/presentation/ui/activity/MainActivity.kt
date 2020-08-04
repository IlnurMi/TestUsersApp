package com.example.testusersapp.presentation.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.model.UserEntity
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.presentation.ui.fragments.ListFragment
import com.example.testusersapp.presentation.ui.fragments.UserFragment

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragments()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun addFragments(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, ListFragment() as Fragment)
            .commit()
    }

    fun replaceFragments(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, UserFragment() as Fragment)
            .commit()
    }
}
