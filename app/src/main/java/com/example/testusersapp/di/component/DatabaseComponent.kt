package com.example.testusersapp.di.component

import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.di.module.DatabaseModule
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppRoomDatabase
}