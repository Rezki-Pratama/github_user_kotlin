package com.dicoding.aplikasi_github_user_2.data.repository

import com.dicoding.aplikasi_github_user_2.data.model.GitDetailUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUserResponse
import com.dicoding.aplikasi_github_user_2.data.remote.GithubApiService

class GithubUserRetrofitRepositoryImpl(
    private val githubApiService: GithubApiService
    ) : GithubUserRepository {

    override suspend fun searchUser(query: String): GitUserResponse {
        return  githubApiService.searchUser(query)
    }

    override suspend fun getDetailUser(name: String): GitDetailUser {
        return githubApiService.getDetailUser(name)
    }

    override suspend fun getFollower(name: String): List<GitUser> {
        return githubApiService.getFollower(name)
    }

    override suspend fun getFollowing(name: String): List<GitUser> {
        return githubApiService.getFollowing(name)
    }
}