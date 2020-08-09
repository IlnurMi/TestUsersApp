package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.testusersapp.presentation.adapters.FriendsAdapter
import com.example.testusersapp.presentation.listeners.FragmentListener
import com.example.testusersapp.presentation.listeners.UserAdapterListener
import com.example.testusersapp.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.user_fragment.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class UserFragment : Fragment(), UserAdapterListener {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var allUsersViewModel: AllUsersViewModel
    private lateinit var fragmentListener: FragmentListener
    private var id: Int? = null
    private var usersList: List<User>? = null
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as FragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.injectUserFragment(this)
        init()
        setListeners()
    }

    private fun init() {
        fragmentListener.setActionbarTitle(requireActivity().getString(R.string.user))
        fragmentListener.setActionbarArrow(true)

        val bundle = this.arguments
        if (bundle != null)
            id = bundle.getInt("id")

        usersList = ArrayList()
        friendsAdapter = FriendsAdapter(usersList as java.util.ArrayList<User>, this)
        rv_friends.layoutManager = LinearLayoutManager(requireContext())
        rv_friends.adapter = friendsAdapter

        allUsersViewModel =
            ViewModelProviders.of(activity!!, factory).get(AllUsersViewModel::class.java)
        id?.let { allUsersViewModel.getUser(it) }

        allUsersViewModel.getUserLiveData()?.observe(this,
            Observer {
                allUsersViewModel.getUserFriends(it.friends)
                setData(it)
            })

        allUsersViewModel.getUserFriendsLiveData().observe(this,
            Observer { populateAdapter(it) })
    }

    private fun setListeners() {

        tv_user_email.setOnClickListener {
            sendEmail(tv_user_email.getString())
        }

        tv_user_phone.setOnClickListener {
            call(tv_user_phone.getString())
        }

        tv_user_coordinate.setOnClickListener {
            maps(tv_user_coordinate.getString())
        }
    }

    private fun populateAdapter(friends: List<User>) {
        friendsAdapter.addItems(friends)
    }

    private fun setData(userModel: User) {
        tv_user_name.setParam(userModel.name)
        tv_user_age.setParam(userModel.age.toString())
        tv_user_company.setParam(userModel.company)
        tv_user_email.setParam(userModel.email)
        tv_user_phone.setParam(userModel.phone)
        tv_user_address.setParam(userModel.address)
        tv_user_about.setParam(userModel.about)
        tv_user_registered.setParam(allUsersViewModel.convertDate(userModel.registered))
        tv_user_coordinate.setParam("${userModel.latitude}, ${userModel.longitude}")
        when (userModel.eyeColor) {
            "brown" -> tv_user_eye_color.setParam(R.drawable.ic_brown_eye)
            "green" -> tv_user_eye_color.setParam(R.drawable.ic_green_eye)
            "blue" -> tv_user_eye_color.setParam(R.drawable.ic_blue_eye)
        }
        when (userModel.favoriteFruit) {
            "apple" -> tv_user_favorite_fruit.setParam(R.drawable.ic_apple)
            "banana" -> tv_user_favorite_fruit.setParam(R.drawable.ic_banana)
            "strawberry" -> tv_user_favorite_fruit.setParam(R.drawable.ic_strawberry)
        }
    }

    override fun selectUser(id: Int) {
        fragmentListener.replaceFragment(id)
    }

    private fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.type = "text/email"
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        startActivity(Intent.createChooser(intent, ""))
    }

    private fun call(number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
        startActivity(intent)
    }

    private fun maps(coordinate: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "geo:${coordinate.substringBefore(',')},${coordinate.substringAfter(',')}?q=${coordinate.substringBefore(
                    ','
                )},${coordinate.substringAfter(',')}"
            )
        )
        startActivity(intent)
    }
}