package com.dicoding.aplikasi_github_user_2.ui.main

import android.content.Context
import androidx.lifecycle.*
import com.dicoding.aplikasi_github_user_2.data.remote.ApiResult
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserRetrofitRepositoryImpl
import com.dicoding.aplikasi_github_user_2.utils.Utils
import kotlinx.coroutines.Dispatchers

class MainViewModel(private  var githubRepository : GithubUserRetrofitRepositoryImpl) : ViewModel() {

    fun searchUser(context: Context, query: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(data = null))
        try {
            emit(ApiResult.success(data = githubRepository.searchUser(query)))
        } catch (exception: Exception) {
            emit(ApiResult.error(data = null, message = exception.message ?: Utils.getErrorMessage(context)))
        }
    }
}