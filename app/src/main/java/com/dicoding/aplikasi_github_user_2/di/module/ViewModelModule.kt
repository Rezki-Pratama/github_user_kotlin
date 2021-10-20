package com.dicoding.aplikasi_github_user_2.di.module

import com.dicoding.aplikasi_github_user_2.ui.detail.DetailViewModel
import com.dicoding.aplikasi_github_user_2.ui.detail.SectionPageViewModel
import com.dicoding.aplikasi_github_user_2.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get())}
    viewModel { SectionPageViewModel(get()) }
}