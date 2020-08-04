package com.example.testusersapp.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment: Fragment(){

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
    }

    fun setData(user: User){
        tv_user_name.setParam(user.name)
        tv_user_age.setParam(user.age.toString())
        tv_user_company.setParam(user.company)
        tv_user_email.setParam(user.email)
        tv_user_phone.setParam(user.phone)
        tv_user_address.setParam(user.address)
        tv_user_about.setParam(user.about)
    }

}