package com.example.testusersapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.presentation.holders.UserViewHolder

class UsersAdapter: RecyclerView.Adapter<UserViewHolder>() {
    private lateinit var users: MutableList<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addItems(list: List<User>){
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }
}