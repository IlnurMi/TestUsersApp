package com.example.testusersapp.di.module

import com.example.testusersapp.data.database.AppRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appRoomDatabase: AppRoomDatabase) {
    @Provides
    fun providesRoomDatabase(): AppRoomDatabase{
        return appRoomDatabase
    }
}