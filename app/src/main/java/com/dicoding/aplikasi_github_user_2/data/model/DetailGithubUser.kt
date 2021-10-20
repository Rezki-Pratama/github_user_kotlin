package com.dicoding.aplikasi_github_user_2.data.model

import com.google.gson.annotations.SerializedName

data class GitDetailUser(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("avatar_url")
    var avatarUrl: String?,
    @SerializedName("followers")
    var followers: String?,
    @SerializedName("following")
    var following: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("company")
    var company: String?,
    @SerializedName("blog")
    var blog: String?,
    @SerializedName("login")
    var login: String?,
    @SerializedName("public_repos")
    var public_repos: String?,
    @SerializedName("public_gists")
    var public_gists: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("location")
    var location: String?,
)