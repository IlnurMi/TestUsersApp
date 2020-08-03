package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.data.repository.AppRepository
import com.example.testusersapp.presentation.adapters.UsersAdapter
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment: Fragment() {

    private lateinit var usersAdapter: UsersAdapter
    private var repository: AppRepository? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

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
        usersAdapter = UsersAdapter()
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = usersAdapter

        repository = (context as App).repositoryComponent?.repository
        repository?.getAllUsers()?.subscribe({result -> success(result)}, {error -> error(error)})
    }

    private fun success(result: List<User>){
        Log.d("TAG", "success: ${result.get(0)}")
        usersAdapter.addItems(result)
    }

    private fun error(error: Throwable){
        Log.d("TAG", "error: $error")
    }
}