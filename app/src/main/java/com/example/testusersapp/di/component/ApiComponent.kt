package com.example.testusersapp.di.component

import com.example.testusersapp.data.network.api.ApiService
import com.example.testusersapp.di.module.ApiModule
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    val apiService: ApiService
}