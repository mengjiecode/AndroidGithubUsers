package com.mj.androidgithubusers.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mj.androidgithubusers.R
import com.mj.androidgithubusers.activity.UserDetailActivity
import com.mj.androidgithubusers.model.User
import com.squareup.picasso.Picasso

class UserAdapter(val users: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        Log.d("Mengjie", "users.size == " + users.size)
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Mengjie", "onBindViewHolder(), position == " + position)
        holder.txtLogin.text = users.get(position).login

        if (users.get(position).site_admin.equals("false")) {
            holder.txtSiteAdmin.visibility = View.GONE
        } else {
            holder.txtSiteAdmin.visibility = View.VISIBLE
        }

        Picasso.get().load(users.get(position).avatar_url).into(holder.imgAvatar);
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgAvatar = itemView.findViewById<ImageView>(R.id.img_avatar)
        val txtLogin = itemView.findViewById<TextView>(R.id.txt_login)
        val txtSiteAdmin = itemView.findViewById<TextView>(R.id.txt_site_admin)

    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}