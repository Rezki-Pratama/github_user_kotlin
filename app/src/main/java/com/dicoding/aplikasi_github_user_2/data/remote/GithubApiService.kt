package com.dicoding.aplikasi_github_user_2.data.remote

import com.dicoding.aplikasi_github_user_2.data.model.GitDetailUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("/search/users")
    suspend  fun searchUser(
        @Query("q") query: String? ): GitUserResponse

    @GET("/users/{name}")
    suspend  fun getDetailUser(
        @Path("name") name: String? ): GitDetailUser

    @GET("/users/{name}/followers")
    suspend  fun getFollower(
        @Path("name") name: String? ): List<GitUser>

    @GET("/users/{name}/following")
    suspend  fun getFollowing(
        @Path("name") name: String? ): List<GitUser>
}