package com.example.testusersapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testusersapp.R
import com.example.testusersapp.domain.models.User
import com.example.testusersapp.presentation.holders.UserViewHolder
import com.example.testusersapp.presentation.listeners.UserAdapterListener

class UsersAdapter(private var userModels: MutableList<User>, private var listener: UserAdapterListener): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return this.userModels.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userModels[position])
    }

    fun addItems(list: List<User>){
        userModels.clear()
        userModels.addAll(list)
        notifyDataSetChanged()
    }
}