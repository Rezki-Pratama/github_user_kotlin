package com.dicoding.aplikasi_github_user_2.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.remote.ApiResult
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserDaoRepository
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserRetrofitRepositoryImpl
import com.dicoding.aplikasi_github_user_2.utils.Utils
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private  var githubRepository : GithubUserRetrofitRepositoryImpl, private var githubDaoRepository : GithubUserDaoRepository) : ViewModel() {

    fun insert(githubUser: GithubUserEntity) {
        githubDaoRepository.insert(githubUser)
    }

    fun delete(githubUser: GithubUserEntity) {
        githubDaoRepository.delete(githubUser)
    }

    fun getDetail(context: Context, name: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(data = null))
        try {
            emit(ApiResult.success(data = githubRepository.getDetailUser(name)))
        } catch (exception: Exception) {
            emit(ApiResult.error(data = null, message = exception.message ?: Utils.getErrorMessage(context)))
        }
    }
}