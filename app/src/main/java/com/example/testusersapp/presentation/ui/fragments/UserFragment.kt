package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.testusersapp.data.model.User
import com.example.testusersapp.domain.AllUsersViewModel
import com.example.testusersapp.domain.ViewModelFactory
import com.example.testusersapp.presentation.adapters.FriendsAdapter
import com.example.testusersapp.presentation.listeners.UserAdapterListener
import com.example.testusersapp.presentation.ui.activity.MainActivity
import kotlinx.android.synthetic.main.user_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserFragment : Fragment(), UserAdapterListener {

    private var id: Int? = null
    private lateinit var allUsersViewModel: AllUsersViewModel
    private var usersList: List<User>? = null
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
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

        (context as MainActivity).supportActionBar?.title =
            (context as MainActivity).getString(R.string.user)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = this.arguments
        if (bundle != null)
            id = bundle.getInt("id")

        usersList = ArrayList()
        friendsAdapter = FriendsAdapter(usersList as java.util.ArrayList<User>, this)
        rv_friends.layoutManager = LinearLayoutManager(requireContext())
        rv_friends.adapter = friendsAdapter

        allUsersViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory((requireActivity().application as App).repositoryComponent!!.repository)
        ).get(
            AllUsersViewModel::class.java
        )
        id?.let { allUsersViewModel.getUser(it) }
        allUsersViewModel.getUserLiveData().observe(this,
            Observer<User> {
                allUsersViewModel.getUserFriends(it.friends)
                setData(it)
            })

        allUsersViewModel.getUserFriendsLiveData().observe(this,
            Observer<List<User>> {
                setAdapters(it)
            })

        setListeners()
    }

    private fun setAdapters(friends: List<User>) {
        friendsAdapter.addItems(friends)
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

    private fun setData(user: User) {
        tv_user_name.setParam(user.name)
        tv_user_age.setParam(user.age.toString())
        tv_user_company.setParam(user.company)
        tv_user_email.setParam(user.email)
        tv_user_phone.setParam(user.phone)
        tv_user_address.setParam(user.address)
        tv_user_about.setParam(user.about)
        tv_user_registered.setParam(convertDate(user.registered))
        tv_user_coordinate.setParam("${user.latitude}, ${user.longitude}")
        when (user.eyeColor) {
            "brown" -> tv_user_eye_color.setParam(R.drawable.ic_brown_eye)
            "green" -> tv_user_eye_color.setParam(R.drawable.ic_green_eye)
            "blue" -> tv_user_eye_color.setParam(R.drawable.ic_blue_eye)
        }
        when (user.favoriteFruit) {
            "apple" -> tv_user_favorite_fruit.setParam(R.drawable.ic_apple)
            "banana" -> tv_user_favorite_fruit.setParam(R.drawable.ic_banana)
            "strawberry" -> tv_user_favorite_fruit.setParam(R.drawable.ic_strawberry)
        }
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
        val uri = String.format(Locale.ENGLISH, "geo:%f,%f", 52.0, 52.0)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${52.0},${52.0}?q=${52.0},${52.0}"))
        startActivity(intent)
    }

    override fun selectUser(id: Int) {
        (context as MainActivity).replaceFragments(id)
    }

    private fun convertDate(date: String): String {
        val dateFormat = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss")
        val result = dateFormat.parse(date)
        val myCal: Calendar = GregorianCalendar()
        myCal.time = result
        Log.d("TAG", "convertDate: ${myCal.get(Calendar.YEAR)}")
        return "${changeTime(result.hours)}:${changeTime(result.minutes)} ${changeTime(result.day)}.${changeTime(
            result.month
        )}.${myCal.get(Calendar.YEAR)}"
    }

    private fun changeTime(value: Int): String {
        if (value < 10)
            return "0$value"
        return "$value"
    }
}