package com.example.testusersapp.di.component

import com.example.testusersapp.di.module.*
import com.example.testusersapp.presentation.ui.fragments.ListFragment
import com.example.testusersapp.presentation.ui.fragments.LoginFragment
import com.example.testusersapp.presentation.ui.fragments.UserFragment
import dagger.Component

@Component(
    modules = [ViewModelModule::class, InteractorModule::class,
        RepositoryModule::class, ApiModule::class,
        DatabaseModule::class]
)
interface AppComponent {
    fun injectListFragment(fragment: ListFragment)
    fun injectUserFragment(fragment: UserFragment)
    fun injectLoginFragment(fragment: LoginFragment)
}