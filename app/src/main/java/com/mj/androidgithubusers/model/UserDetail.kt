package com.mj.androidgithubusers.model

data class UserDetail(var avatar_url: String = "",
                      var login: String = "",
                      var site_admin: String = "false",
                      var name: String = "",
                      var location: String = "",
                      var bio: String = "",
                      var blog: String = "") {
}