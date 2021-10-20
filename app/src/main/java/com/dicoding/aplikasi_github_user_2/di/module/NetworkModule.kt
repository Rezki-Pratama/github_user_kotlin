package com.dicoding.aplikasi_github_user_2.di.module

import com.dicoding.aplikasi_github_user_2.data.remote.GithubApiService
import com.dicoding.aplikasi_github_user_2.utils.Constants
import org.koin.dsl.module
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }



    fun provideGithubApi(retrofit: Retrofit): GithubApiService = retrofit.create(GithubApiService::class.java)

    factory { provideOkHttpClient(get()) }
    factory { provideGithubApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

}