package com.dicoding.aplikasi_github_user_2.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.aplikasi_github_user_2.data.local.GithubUserRoomDatabase
import com.dicoding.aplikasi_github_user_2.data.local.ThemeModeDao
import com.dicoding.aplikasi_github_user_2.data.local.ThemeModeRoomDatabase
import com.dicoding.aplikasi_github_user_2.data.model.ThemeModeEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThemeModeDaoRepository(application: Application) {
    private val mThemeModeDao: ThemeModeDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = ThemeModeRoomDatabase.getDatabase(application)
        mThemeModeDao = db.modeDao()
    }

    fun getIsMode(id: Int) : LiveData<Int> = mThemeModeDao.isFavorite(id)

    fun insert(mode: ThemeModeEntity) {
        executorService.execute { mThemeModeDao.insert(mode) }
    }
    fun delete(mode: ThemeModeEntity) {
        executorService.execute { mThemeModeDao.delete(mode) }
    }
    fun update(mode: ThemeModeEntity) {
        executorService.execute { mThemeModeDao.update(mode) }
    }
}