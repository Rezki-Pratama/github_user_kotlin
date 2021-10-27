package com.dicoding.aplikasi_github_user_2.di.module

import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserDaoRepository
import com.dicoding.aplikasi_github_user_2.data.repository.GithubUserRetrofitRepositoryImpl
import com.dicoding.aplikasi_github_user_2.data.repository.ThemeModeDaoRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { GithubUserRetrofitRepositoryImpl(get()) }
    single { GithubUserDaoRepository(get()) }
    single { ThemeModeDaoRepository(get()) }
}