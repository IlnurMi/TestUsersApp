package com.example.testusersapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testusersapp.data.database.AppRoomDatabase

class App: Application() {
    private var database: AppRoomDatabase? = null

    override fun onCreate() {
        super.onCreate()
    }

    private fun initRoom(){
        database = Room
            .databaseBuilder(applicationContext, AppRoomDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger(){

    }
}