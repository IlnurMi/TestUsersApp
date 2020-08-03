package com.example.testusersapp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.data.preference.PreferenceRepository
import com.example.testusersapp.di.component.DaggerApiComponent
import com.example.testusersapp.di.component.DaggerDatabaseComponent
import com.example.testusersapp.di.component.DaggerRepositoryComponent
import com.example.testusersapp.di.component.RepositoryComponent
import com.example.testusersapp.di.module.ApiModule
import com.example.testusersapp.di.module.DatabaseModule
import com.example.testusersapp.di.module.RepositoryModule

class App: Application() {
    private var preferenceRepository: PreferenceRepository? = null
    private var database: AppRoomDatabase? = null
    var repositoryComponent: RepositoryComponent? = null

    override fun onCreate() {
        super.onCreate()
        initPreference()
        initRoom()
        initDagger()
    }

    private fun initPreference(){
        preferenceRepository = PreferenceRepository.getInstance()
        preferenceRepository?.init(applicationContext)
        preferenceRepository?.saveToken(getString(R.string.token))
    }

    private fun initRoom(){
        database = Room
            .databaseBuilder(this, AppRoomDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger(){
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this.database!!))
            .build()

        this.repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()
    }
}