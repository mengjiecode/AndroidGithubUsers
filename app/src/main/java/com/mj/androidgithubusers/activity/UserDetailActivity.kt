package com.mj.androidgithubusers.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mj.androidgithubusers.R
import com.mj.androidgithubusers.model.UserDetail
import com.mj.androidgithubusers.rest.ApiClient
import com.mj.androidgithubusers.rest.ApiInterface
import com.squareup.picasso.Picasso

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        var username: String = intent.getStringExtra("username")

        loadUserDetail(username)
    }

    fun loadUserDetail(user: String) {
        Log.d("Mengjie", "loadUserDetail()")

        val apiInterface: ApiInterface = ApiClient().retrofit!!.create(ApiInterface::class.java)

        val call: Call<UserDetail> = apiInterface.getUserDetail(user)
        call.enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                val statusCode = response.code()
                Log.d("Mengjie", "onResponse(), statusCode == $statusCode")

                val userDetail: UserDetail = response.body() as UserDetail

                Log.d("Mengjie", "----------------------------------------")
                Log.d("Mengjie", "onResponse(), avatar url == " + userDetail.avatar_url)
                Log.d("Mengjie", "onResponse(), is site admin == " + userDetail.site_admin)
                Log.d("Mengjie", "onResponse(), login == " + userDetail.login)
                Log.d("Mengjie", "onResponse(), name == " + userDetail.name)
                Log.d("Mengjie", "onResponse(), location == " + userDetail.location)
                Log.d("Mengjie", "onResponse(), bio == " + userDetail.bio)
                Log.d("Mengjie", "onResponse(), blog == " + userDetail.blog)

                updateUI(userDetail)
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Log.d("Mengjie", "onFailure()")
            }
        })
    }

    fun updateUI(userDetail: UserDetail) {
        img_close.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                finish()
            }
        })

        txt_login.text = userDetail.login
        txt_blog.text = userDetail.blog
        txt_location.text = userDetail.location
        txt_name.text = userDetail.name
        txt_bio.text = userDetail.bio

        if (userDetail.site_admin.equals("false")) {
            txt_site_admin.visibility = View.GONE
        } else {
            txt_site_admin.visibility = View.VISIBLE
        }

        Picasso.get().load(userDetail.avatar_url).into(img_avatar);

    }

}
