package com.example.testusersapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.domain.AllUsersViewModel
import com.example.testusersapp.presentation.adapters.UsersAdapter
import com.example.testusersapp.presentation.listeners.UserAdapterListener
import com.example.testusersapp.presentation.ui.activity.MainActivity
import kotlinx.android.synthetic.main.list_fragment.*
import java.util.ArrayList

class ListFragment: Fragment(), UserAdapterListener {

    private lateinit var usersAdapter: UsersAdapter
    private var repository: AppRepository? = null
    private var usersList: List<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        usersList = ArrayList()
        usersAdapter = UsersAdapter(usersList as ArrayList<User>, this)
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = usersAdapter

        repository = (activity?.applicationContext as App).repositoryComponent?.repository
        repository?.getAllUsersWithDatabase()?.subscribe({result -> success(result)}, {error -> error(error)})
    }

    private fun success(result: List<User>){
        Log.d("TAG", "success: ${result[0]}")
        usersAdapter.addItems(result)
    }

    private fun error(error: Throwable){
        Log.d("TAG", "error: $error")
    }

    override fun selectUser(id: Int) {
        (context as MainActivity).replaceFragments()
        //Toast.makeText(activity, "selected user", Toast.LENGTH_SHORT).show()
        //TODO("Not yet implemented")
    }
}