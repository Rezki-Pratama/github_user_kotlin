package com.dicoding.aplikasi_github_user_2.ui.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserDaoRepository

class FavoritesViewModel(application: Application) : ViewModel() {
    private val mGithubRepository: GithubUserDaoRepository = GithubUserDaoRepository(application)
    fun getAllFavorites(): LiveData<List<GithubUserEntity>> = mGithubRepository.getAllFavorites()
    fun getIsFavorite(id: Int) : LiveData<Int> = mGithubRepository.getIsFavorites(id)
    fun insert(user: GithubUserEntity) = mGithubRepository.insert(user)
    fun delete(user: GithubUserEntity) = mGithubRepository.delete(user)
}