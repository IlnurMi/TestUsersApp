package com.example.testusersapp.di.module

import com.example.testusersapp.domain.interactors.LoginInteractorImp
import com.example.testusersapp.domain.interactors.MainInteractorImp
import com.example.testusersapp.domain.interfaces.interactors.LoginInteractor
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import com.example.testusersapp.domain.interfaces.repositories.Repository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(repository: Repository): MainInteractor{
        return MainInteractorImp(repository)
    }

    @Provides
    fun provideLoginInteractor(repository: Repository): LoginInteractor {
        return LoginInteractorImp(repository)
    }
}