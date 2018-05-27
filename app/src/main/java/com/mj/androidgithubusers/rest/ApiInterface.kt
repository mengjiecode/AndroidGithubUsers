package com.mj.androidgithubusers.rest

import com.mj.androidgithubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("users")
    fun getUsers(): Call<List<User>>

}
