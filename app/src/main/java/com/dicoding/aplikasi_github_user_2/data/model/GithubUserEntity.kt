package com.dicoding.aplikasi_github_user_2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class GithubUserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = "",

    @ColumnInfo(name = "followers")
    var followers: String? = "",

    @ColumnInfo(name = "following")
    var following: String? = "",

    @ColumnInfo(name = "name")
    var name: String? = "",

    @ColumnInfo(name = "company")
    var company: String? = "",

    @ColumnInfo(name = "blog")
    var blog: String? = "",

    @ColumnInfo(name = "login")
    var login: String? = "",

    @ColumnInfo(name = "public_repos")
    var public_repos: String? = "",

    @ColumnInfo(name = "public_gists")
    var public_gists: String? = "",

    @ColumnInfo(name = "type")
    var type: String? = "",

    @ColumnInfo(name = "location")
    var location: String? = "",
) : Parcelable