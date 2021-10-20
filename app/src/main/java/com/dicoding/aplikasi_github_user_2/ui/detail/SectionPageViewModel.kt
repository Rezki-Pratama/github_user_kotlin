package com.dicoding.aplikasi_github_user_2.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.aplikasi_github_user_2.data.remote.ApiResult
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserRetrofitRepositoryImpl
import com.dicoding.aplikasi_github_user_2.utils.Utils
import kotlinx.coroutines.Dispatchers

class SectionPageViewModel(private  var githubRepository : GithubUserRetrofitRepositoryImpl) : ViewModel() {

    fun getFollower(context: Context, name: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(data = null))
        try {
            emit(ApiResult.success(data = githubRepository.getFollower(name)))
        } catch (exception: Exception) {
            emit(ApiResult.error(data = null, message = exception.message ?: Utils.getErrorMessage(context)))
        }
    }

    fun getFollowing(context: Context, name: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(data = null))
        try {
            emit(ApiResult.success(data = githubRepository.getFollowing(name)))
        } catch (exception: Exception) {
            emit(ApiResult.error(data = null, message = exception.message ?: Utils.getErrorMessage(context)))
        }
    }
}