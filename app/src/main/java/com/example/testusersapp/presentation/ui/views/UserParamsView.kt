package com.example.testusersapp.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testusersapp.R
import kotlinx.android.synthetic.main.user_params_view.view.*

class UserParamsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.user_params_view, this, true)
        applyAttributes(attrs)
    }

    private fun applyAttributes(attrs: AttributeSet?){
        context.theme.obtainStyledAttributes(attrs, R.styleable.UserParamsView, 0 ,0).apply {
            try {
                setTitle(getString(R.styleable.UserParamsView_title))
                setParam(getString(R.styleable.UserParamsView_subtitle))
            } finally {
                recycle()
            }
        }
    }

    private fun setTitle(title: String?){
        if (title.isNullOrEmpty())
            tv_user_params_title.text = context.getString(R.string.not_data)
        else
            tv_user_params_title.text = title
    }

    fun setParam(param: String?){
        if (param.isNullOrEmpty())
            tv_user_params.text = context.getString(R.string.not_data)
        else
            tv_user_params.text = param
    }

    fun getString(): String{
        return tv_user_params.text.toString()
    }
}