package com.mj.androidgithubusers.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList


data class User(var avatar_url: String = "",
                var login: String = "",
                var site_admin: String = "false") {

}
