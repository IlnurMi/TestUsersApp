package com.example.testusersapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testusersapp.R
import com.example.testusersapp.domain.models.User
import com.example.testusersapp.presentation.holders.FriendsViewHolder
import com.example.testusersapp.presentation.listeners.UserAdapterListener

class FriendsAdapter(private var friends: MutableList<User>, private var listener: UserAdapterListener): RecyclerView.Adapter<FriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friends_item, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(friends[position])
    }

    fun addItems(list: List<User>){
        friends.clear()
        friends.addAll(list)
        notifyDataSetChanged()
    }
}