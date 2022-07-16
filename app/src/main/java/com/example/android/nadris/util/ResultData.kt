package com.example.android.nadris.util

import android.util.Log
import com.example.android.nadris.NadrisApplication

sealed class Result<T>(
    val data: T? = null,
    val error: String? = null
) {
    private val TAG = "class Result<T>"
     class Success<T>(data: T) : Result<T>(data = data)
     class Loading<T>(data: T? = null) : Result<T>(data = data)
     class Error<T>(error: String, data: T? = null) : Result<T>(data = data, error =  error)
    fun handleRepoResponse(
        onPreExecute:()->Unit = { },
        onLoading:  () -> Unit = { },
        onError:  () -> Unit,
        onSuccess:  () -> Unit){

        onPreExecute()

        when(this)
        {
            is Loading->onLoading()
            is Error -> {
                NadrisApplication.instance?.connectivityMonitor?.checkConnectivity() ?: Log.v(TAG,"couldn't check connectivity null")
                onError()
            }
            is Success -> onSuccess()
        }
    }
}
