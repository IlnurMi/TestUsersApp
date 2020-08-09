package com.example.testusersapp.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.testusersapp.data.utils.ConstantUtils

class PreferenceRepository private constructor() {
    private lateinit var preference: SharedPreferences

    // singleton
    companion object {
        private var INSTANCE: PreferenceRepository? = null
        fun getInstance(): PreferenceRepository{
            if (INSTANCE == null)
                INSTANCE = PreferenceRepository()
            return INSTANCE!!
        }
    }

    fun init(context: Context) {
        preference =
            context.getSharedPreferences(ConstantUtils.APP_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        val editor = preference.edit()
        editor.putString(ConstantUtils.TOKEN, token)
        editor.apply()
    }

    fun getToken(): String = preference.getString(ConstantUtils.TOKEN, "")
}