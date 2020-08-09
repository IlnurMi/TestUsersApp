package com.example.testusersapp

import android.app.Application
import androidx.room.Room
import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.data.preference.PreferenceRepository
import com.example.testusersapp.di.component.AppComponent
import com.example.testusersapp.di.component.DaggerAppComponent
import com.example.testusersapp.di.module.*

class App : Application() {
    private var preferenceRepository: PreferenceRepository? = null
    private var database: AppRoomDatabase? = null
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initPreference()
        initRoom()
        initDagger()
    }

    private fun initPreference() {
        preferenceRepository = PreferenceRepository.getInstance()
        preferenceRepository?.init(applicationContext)
        preferenceRepository?.saveToken(getString(R.string.token))
    }

    private fun initRoom() {
        database = Room
            .databaseBuilder(this, AppRoomDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule(this.database!!))
            .repositoryModule(RepositoryModule())
            .interactorModule(InteractorModule()).viewModelModule(ViewModelModule())
            .build()
    }
}