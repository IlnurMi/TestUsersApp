package com.example.testusersapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.domain.models.User
import com.example.testusersapp.presentation.viewmodels.AllUsersViewModel
import com.example.testusersapp.presentation.viewmodels.ViewModelFactory
import com.example.testusersapp.presentation.adapters.UsersAdapter
import com.example.testusersapp.presentation.listeners.UserAdapterListener
import com.example.testusersapp.presentation.ui.activity.MainActivity
import kotlinx.android.synthetic.main.list_fragment.*
import java.util.ArrayList

class ListFragment: Fragment(), UserAdapterListener {

    private lateinit var usersAdapter: UsersAdapter
    private var repository: AppRepository? = null
    private var usersList: List<User>? = null
    private lateinit var allUsersViewModel: AllUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).supportActionBar?.title = activity?.getString(R.string.app_name)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        init()
/*        allUsersViewModel = ViewModelProviders.of(this,
            ViewModelFactory((requireActivity().application as App).repositoryComponent!!.repository)
        ).get(
            AllUsersViewModel::class.java)
        allUsersViewModel.getLiveDataUsers()?.observe(this,
            Observer<List<User>> {
                success(it)
            })*/
    }

    private fun init(){
        usersList = ArrayList()
        usersAdapter = UsersAdapter(usersList as ArrayList<User>, this)
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = usersAdapter
    }

    private fun success(result: List<User>){
        usersAdapter.addItems(result)
    }

    private fun error(error: Throwable){
        Log.d("TAG", "error: $error")
    }

    override fun selectUser(id: Int) {
        (context as MainActivity).replaceFragments(id)
    }
}