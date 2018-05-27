package com.mj.androidgithubusers.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.mj.androidgithubusers.R
import com.mj.androidgithubusers.adapter.UserAdapter
import com.mj.androidgithubusers.model.User
import com.mj.androidgithubusers.rest.ApiClient
import com.mj.androidgithubusers.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Mengjie", "onCreate()")

        callUserApi()

        rv_user_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


    }

    fun callUserApi() {
        Log.d("Mengjie", "callUserApi()")

        val apiService: ApiInterface = ApiClient().retrofit!!.create(ApiInterface::class.java)

        val call: Call<List<User>> = apiService.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val statusCode = response.code()
                Log.d("Mengjie", "onResponse(), statusCode == $statusCode")

                val users: List<User> = response.body() as List<User>
                Log.d("Mengjie", "onResponse(), response.body().size == " + response.body()!!.size)
                for (i in users!!.indices) {
                    Log.d("Mengjie", "----------------------------------------")
                    Log.d("Mengjie", "onResponse(), avatar url == " + users.get(i).avatar_url)
                    Log.d("Mengjie", "onResponse(), is site admin == " + users.get(i).site_admin)
                    Log.d("Mengjie", "onResponse(), login == " + users.get(i).login)
                }

                rv_user_list.adapter = UserAdapter(users)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Mengjie", "onFailure()")
            }
        })
    }
}
