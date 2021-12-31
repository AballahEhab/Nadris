package com.example.android.nadris.util

sealed class ResultData<T> (val data:T?=null,val error:Throwable? =  null){

    class Success<T>(data:T):ResultData<T>(data)
    class Loading<T>(data:T?=null):ResultData<T>(data)
    class Error<T>(throwable: Throwable? = null,data: T?):ResultData<T>(data,throwable )
}