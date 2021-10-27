package com.dicoding.aplikasi_github_user_2.ui.main

import android.content.Context
import androidx.lifecycle.*
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.model.ThemeModeEntity
import com.dicoding.aplikasi_github_user_2.data.remote.ApiResult
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserRetrofitRepositoryImpl
import com.dicoding.aplikasi_github_user_2.data.repository.ThemeModeDaoRepository
import com.dicoding.aplikasi_github_user_2.utils.Utils
import kotlinx.coroutines.Dispatchers

class MainViewModel(private  var githubRepository : GithubUserRetrofitRepositoryImpl, private var themeModeDaoRepository: ThemeModeDaoRepository) : ViewModel() {

    fun searchUser(context: Context, query: String) = liveData(Dispatchers.IO) {
        emit(ApiResult.loading(data = null))
        try {
            emit(ApiResult.success(data = githubRepository.searchUser(query)))
        } catch (exception: Exception) {
            emit(ApiResult.error(data = null, message = exception.message ?: Utils.getErrorMessage(context)))
        }
    }

    fun getIsMode(mode: Int) : LiveData<Int> = themeModeDaoRepository.getIsMode(mode)
    fun insert(mode: ThemeModeEntity) = themeModeDaoRepository.insert(mode)
    fun update(mode: ThemeModeEntity) = themeModeDaoRepository.update(mode)
    fun delete(mode: ThemeModeEntity) = themeModeDaoRepository.delete(mode)
}