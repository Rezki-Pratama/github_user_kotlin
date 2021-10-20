package com.dicoding.aplikasi_github_user_2.utils

import com.dicoding.aplikasi_github_user_2.BuildConfig
import com.dicoding.aplikasi_github_user_2.utils.Constants.BASE_URL

object Constants {

    const val BASE_URL= "https://api.github.com/"

    const val MOVE_BUNDLE_ITEM_SEARCH_RESULT = "move_bundle_item_search_result"

    enum class API_STATUS {
        SUCCESS,
        ERROR,
        LOADING
    }
}