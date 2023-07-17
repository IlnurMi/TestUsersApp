package com.example.testusersapp.di.module

import com.example.testusersapp.domain.interfaces.interactors.LoginInteractor
import com.example.testusersapp.domain.interfaces.interactors.MainInteractor
import com.example.testusersapp.presentation.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideUserViewModelFactory(interactor: MainInteractor, loginInteractor: LoginInteractor): ViewModelFactory{
        return ViewModelFactory(interactor, loginInteractor)
    }
}