package com.example.testusersapp.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testusersapp.R
import kotlinx.android.synthetic.main.view_custom_keyboard.view.*
import java.util.logging.Handler


class CustomKeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_keyboard, this, true)
    }

    private var mListener: OnFragmentInteractionListener? = null

    private fun setIsClickable(isEnabled: Boolean) {
        listOf(
            s1,
            s2,
            s3,
            s4,
            s5,
            s6,
            s7,
            s8,
            s9,
            s0
        ).forEach {
            it.isClickable = isEnabled
        }
    }

    init {
        setListeners()
    }

    private fun setListeners() {
        s0.setOnClickListener { mListener?.onKeyboardClick(s0.tag.toString()) }
        s1.setOnClickListener { mListener?.onKeyboardClick(s1.tag.toString()) }
        s2.setOnClickListener { mListener?.onKeyboardClick(s2.tag.toString()) }
        s3.setOnClickListener { mListener?.onKeyboardClick(s3.tag.toString()) }
        s4.setOnClickListener { mListener?.onKeyboardClick(s4.tag.toString()) }
        s5.setOnClickListener { mListener?.onKeyboardClick(s5.tag.toString()) }
        s6.setOnClickListener { mListener?.onKeyboardClick(s6.tag.toString()) }
        s7.setOnClickListener { mListener?.onKeyboardClick(s7.tag.toString()) }
        s8.setOnClickListener { mListener?.onKeyboardClick(s8.tag.toString()) }
        s9.setOnClickListener { mListener?.onKeyboardClick(s9.tag.toString()) }
    }

    fun setOnKeyboardListener(listener: OnFragmentInteractionListener) {
        mListener = listener
    }

    fun setEnabled() {
        setIsClickable(false)
        android.os.Handler().postDelayed(Runnable {
            setIsClickable(true)
        }, 10000)
    }

    interface OnFragmentInteractionListener {

        fun onKeyboardClick(symbol: String)
    }
}