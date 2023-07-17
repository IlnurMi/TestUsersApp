package com.example.testusersapp.presentation.listeners

interface FragmentListener {
    fun setActionbarTitle(title: String)
    fun setActionbarArrow(show: Boolean)
    fun replaceFragment(id: Int)
    fun replaceLoginFragment()
}