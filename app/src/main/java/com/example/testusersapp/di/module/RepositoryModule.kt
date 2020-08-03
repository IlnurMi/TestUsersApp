package com.example.testusersapp.di.module

import com.example.testusersapp.data.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(): AppRepository{
        return AppRepository()
    }
}