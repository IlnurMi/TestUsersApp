package com.example.testusersapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.testusersapp.R
import com.example.testusersapp.presentation.listeners.FragmentListener
import com.example.testusersapp.presentation.ui.fragments.ListFragment
import com.example.testusersapp.presentation.ui.fragments.UserFragment

class MainActivity : AppCompatActivity(), FragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            addFragments()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addFragments() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, ListFragment() as Fragment)
            .commit()
    }

    override fun setActionbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setActionbarArrow(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    override fun replaceFragment(id: Int) {
        val arguments = Bundle()
        arguments.putInt("id", id)
        val userFragment = UserFragment()
        userFragment.arguments = arguments
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, userFragment as Fragment)
            .addToBackStack(null)
            .commit()
    }
}
