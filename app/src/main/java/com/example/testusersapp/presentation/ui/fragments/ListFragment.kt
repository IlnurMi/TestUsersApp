package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testusersapp.App
import com.example.testusersapp.R
import com.example.testusersapp.domain.models.User
import com.example.testusersapp.presentation.viewmodels.AllUsersViewModel
import com.example.testusersapp.presentation.adapters.UsersAdapter
import com.example.testusersapp.presentation.listeners.FragmentListener
import com.example.testusersapp.presentation.listeners.UserAdapterListener
import com.example.testusersapp.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.list_fragment.*
import java.util.ArrayList
import javax.inject.Inject

class ListFragment : Fragment(), UserAdapterListener {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var allUsersViewModel: AllUsersViewModel
    private lateinit var fragmentListener: FragmentListener
    private lateinit var usersAdapter: UsersAdapter
    private var usersList: List<User>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as FragmentListener
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
        (requireActivity().application as App).appComponent.injectListFragment(this)
        init()
        setListeners()
    }

    private fun init() {
        fragmentListener.setActionbarTitle(requireActivity().getString(R.string.app_name))
        fragmentListener.setActionbarArrow(false)

        usersList = ArrayList()
        usersAdapter = UsersAdapter(usersList as ArrayList<User>, this)
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = usersAdapter

        allUsersViewModel = ViewModelProviders.of(activity!!, factory).get(AllUsersViewModel::class.java)
        allUsersViewModel.getLiveDataUsers()?.observe(this,
            Observer {
                swipe_refresh_layout.isRefreshing = false
                populateAdapter(it)
            })
    }

    private fun setListeners(){
        swipe_refresh_layout.setOnRefreshListener {
            allUsersViewModel.updateUsers()
        }
    }

    private fun populateAdapter(result: List<User>) {
        usersAdapter.addItems(result)
    }

    override fun selectUser(id: Int) {
        fragmentListener.replaceFragment(id)
    }
}