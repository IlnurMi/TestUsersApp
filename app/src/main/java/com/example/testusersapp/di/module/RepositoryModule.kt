package com.example.testusersapp.di.module

import com.example.testusersapp.data.database.AppRoomDatabase
import com.example.testusersapp.data.network.api.ApiService
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.domain.interfaces.repositories.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(apiService: ApiService, database: AppRoomDatabase): Repository{
        return AppRepository(apiService, database)
    }
}