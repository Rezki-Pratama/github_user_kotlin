package com.dicoding.aplikasi_github_user_2.data.repository

import com.dicoding.aplikasi_github_user_2.data.model.GitDetailUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUserResponse

interface GithubUserRepository {

    suspend fun searchUser(query:String): GitUserResponse
    suspend fun getDetailUser(name:String): GitDetailUser
    suspend fun getFollower(name:String): List<GitUser>
    suspend fun getFollowing(name:String): List<GitUser>
}