package com.example.testusersapp.data.network.api

import com.example.testusersapp.data.model.UserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&")
    fun getUsers(@Query("token") token: String): Single<List<UserModel>>
}