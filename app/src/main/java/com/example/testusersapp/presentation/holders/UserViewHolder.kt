package com.example.testusersapp.presentation.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testusersapp.R
import com.example.testusersapp.data.model.User
import com.example.testusersapp.presentation.listeners.UserAdapterListener

class UserViewHolder(itemView: View, private var listener: UserAdapterListener): RecyclerView.ViewHolder(itemView) {
    private var tvName: TextView = itemView.findViewById(R.id.tv_name)
    private var tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    private var ivActive: ImageView = itemView.findViewById(R.id.iv_active)
    private var itemLayout: ConstraintLayout = itemView.findViewById(R.id.item_layout)

    fun bind(item: User){
        tvName.text = item.name
        tvEmail.text = item.email

        if (item.isActive)
            ivActive.visibility = View.VISIBLE
        else
            ivActive.visibility = View.GONE

        itemLayout.setOnClickListener {
            if (item.isActive)
                listener.selectUser(item.id)
        }

    }
}