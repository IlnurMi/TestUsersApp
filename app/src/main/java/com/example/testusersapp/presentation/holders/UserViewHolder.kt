package com.example.testusersapp.presentation.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var tvName: TextView = itemView.findViewById(R.id.tv_name)
    var tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    var ivActive: ImageView = itemView.findViewById(R.id.iv_active)

    fun bind(item: User){
        tvName.text = item.name
        tvEmail.text = item.email

        if (item.isActive)
            ivActive.visibility = View.VISIBLE
        else
            ivActive.visibility = View.GONE
    }
}