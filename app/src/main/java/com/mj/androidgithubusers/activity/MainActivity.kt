package com.mj.androidgithubusers.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
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

    var userList: List<User>? = null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Mengjie", "onCreate()")

        rv_user_list.layoutManager = LinearLayoutManager(
                applicationContext, LinearLayout.VERTICAL, false)

         rv_user_list.addOnItemClickListener(object: OnItemClickListener {
             override fun onItemClicked(position: Int, view: View) {
                 // Your logic
                 Log.d("Mengjie", "onItemClicked(), position == " + position)
                 showUserDetail(position)
             }
         })

        loadUsers()

    }

    fun showUserDetail(position: Int) {
        val intent = Intent(applicationContext, UserDetailActivity::class.java)
        intent.putExtra("username", userList!!.get(position).login)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(intent)
    }

    fun loadUsers() {
        Log.d("Mengjie", "loadUsers()")

        val apiInterface: ApiInterface = ApiClient().retrofit!!.create(ApiInterface::class.java)

        val call: Call<List<User>> = apiInterface.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val statusCode = response.code()
                Log.d("Mengjie", "onResponse(), statusCode == $statusCode")

                val users: List<User> = response.body() as List<User>
                Log.d("Mengjie", "onResponse(), users.size == " + users.size)
                for (i in users!!.indices) {
                    Log.d("Mengjie", "----------------------------------------")
                    Log.d("Mengjie", "onResponse(), avatar url == " + users.get(i).avatar_url)
                    Log.d("Mengjie", "onResponse(), is site admin == " + users.get(i).site_admin)
                    Log.d("Mengjie", "onResponse(), login == " + users.get(i).login)
                }
                userList = users
                rv_user_list.adapter = UserAdapter(users)
                rv_user_list.adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Mengjie", "onFailure()")
            }
        })
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View?) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View?) {
                view?.setOnClickListener({
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                })
            }
        })
    }


}
