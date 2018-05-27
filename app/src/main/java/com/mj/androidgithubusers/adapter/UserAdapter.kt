package com.mj.androidgithubusers.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mj.androidgithubusers.R
import com.mj.androidgithubusers.model.User

class UserAdapter(val userList: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtLogin.text = userList.get(position).login
        holder.txtSiteAdmin.text = "" + userList.get(position).site_admin
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtLogin = itemView.findViewById<TextView>(R.id.txt_login)
        val txtSiteAdmin = itemView.findViewById<TextView>(R.id.txt_site_admin)

    }

}