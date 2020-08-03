package com.example.testusersapp.di.component

import com.example.testusersapp.di.module.RepositoryModule
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
}