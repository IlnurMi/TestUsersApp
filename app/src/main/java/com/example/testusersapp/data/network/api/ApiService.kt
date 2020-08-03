package com.example.testusersapp.data.network.api

import com.example.testusersapp.data.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    fun getUsers(): Single<List<User>>

}