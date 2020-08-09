package com.example.testusersapp.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testusersapp.R
import kotlinx.android.synthetic.main.user_image_view.view.*

class UserImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.user_image_view, this, true)
        applyAttributes(attrs)
    }

    private fun applyAttributes(attrs: AttributeSet?){
        context.theme.obtainStyledAttributes(attrs, R.styleable.UserImageView, 0 ,0).apply {
            try {
                setTitle(getString(R.styleable.UserImageView_title))
            } finally {
                recycle()
            }
        }
    }

    private fun setTitle(title: String?){
        if (title.isNullOrEmpty())
            tv_user_image_title.text = context.getString(R.string.not_data)
        else
            tv_user_image_title.text = title
    }

    fun setParam(param: Int){
        if (param == null)
            iv_user_image.setImageResource(R.drawable.ic_active_true)
        else
            iv_user_image.setImageResource(param)
    }
}