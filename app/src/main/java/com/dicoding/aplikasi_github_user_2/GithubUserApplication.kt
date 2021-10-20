package com.dicoding.aplikasi_github_user_2

import android.app.Application
import com.dicoding.aplikasi_github_user_2.di.component.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubUserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinDI()
    }

    private fun initKoinDI() = startKoin {
        androidContext(this@GithubUserApplication)
        modules(appComponent)
    }
}