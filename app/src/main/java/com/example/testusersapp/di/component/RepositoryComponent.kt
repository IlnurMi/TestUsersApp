package com.example.testusersapp.di.component

import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.di.module.RepositoryModule
import dagger.Component

@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}