package com.example.flowtechticstask.utils

import com.example.flowtechticstask.R
import retrofit2.HttpException
import java.io.IOException


fun Throwable.toNetworkError(): Int {
    return when (this) {
        is IOException -> R.string.noInternetConnection

        is HttpException -> {
            when (this.code()) {
                400 -> R.string.not_found
                500 -> R.string.server_error
                1400 -> R.string.noInternetConnection
                else -> R.string.unknown
            }
        }

        else -> R.string.unknown
    }

}