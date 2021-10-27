package com.dicoding.aplikasi_github_user_2.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.aplikasi_github_user_2.data.local.GithubUserDao
import com.dicoding.aplikasi_github_user_2.data.local.GithubUserRoomDatabase
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GithubUserDaoRepository(application: Application) {
    private val mGithubUserDao: GithubUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = GithubUserRoomDatabase.getDatabase(application)
        mGithubUserDao = db.noteDao()
    }
    fun getAllFavorites(): LiveData<List<GithubUserEntity>> = mGithubUserDao.getAllFavorites()

    fun getIsFavorites(id: Int) : LiveData<Int> = mGithubUserDao.isFavorite(id)

    fun insert(user: GithubUserEntity) {
        executorService.execute { mGithubUserDao.insert(user) }
    }
    fun delete(user: GithubUserEntity) {
        executorService.execute { mGithubUserDao.delete(user) }
    }
    fun update(user: GithubUserEntity) {
        executorService.execute { mGithubUserDao.update(user) }
    }
}