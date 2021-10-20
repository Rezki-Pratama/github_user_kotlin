package com.dicoding.aplikasi_github_user_2.data.remote

import com.dicoding.aplikasi_github_user_2.utils.Constants


data class ApiResult<out T>(val status: Constants.API_STATUS, val data: T?, val message: String?) {
    companion object {

        fun <T> loading(data: T?): ApiResult<T> = ApiResult(status = Constants.API_STATUS.LOADING, data = data, message = null)

        fun <T> success(data: T): ApiResult<T> = ApiResult(status = Constants.API_STATUS.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResult<T> = ApiResult(status = Constants.API_STATUS.ERROR, data = data, message = message)

    }
}