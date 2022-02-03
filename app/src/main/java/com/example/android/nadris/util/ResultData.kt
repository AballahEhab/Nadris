package com.example.android.nadris.util

import retrofit2.Response
import java.net.HttpURLConnection

sealed class Result<T>(
    val data: T? = null,
    val error: String? = null
) {
     class Success<T>(data: T) : Result<T>(data = data)
     class Loading<T>(data: T? = null) : Result<T>(data = data)
     class Error<T>(error: String, data: T? = null) : Result<T>(data = data, error =  error)

        fun handleRepoResponse(
        onLoading:  () -> Unit,
        onError:  () -> Unit,
        onSuccess:  () -> Unit,){

        when(this)
        {
            is Loading->onLoading()
            is Error -> onError()
            is Success -> onSuccess()
        }
    }
}
