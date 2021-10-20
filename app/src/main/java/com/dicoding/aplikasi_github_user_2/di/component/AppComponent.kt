package com.dicoding.aplikasi_github_user_2.di.component

import com.dicoding.aplikasi_github_user_2.di.module.networkModule
import com.dicoding.aplikasi_github_user_2.di.module.repositoryModule
import com.dicoding.aplikasi_github_user_2.di.module.viewModelModule

val appComponent = listOf(
    viewModelModule,
    repositoryModule,
    networkModule
)