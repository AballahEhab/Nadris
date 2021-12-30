package com.example.android.nadris.network

import retrofit2.Response
import javax.inject.Inject

class UserDataSource @Inject constructor(private val apiService: NadrisAPIService, val model : LoginAccountModel) : BaseDataSource() {

    suspend fun getData() = getData { apiService.login(model) }

}
abstract class BaseDataSource {

    protected suspend fun <T> getData(call: suspend () -> Response<T>): ResultData<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultData.success(body)
            }
            return formateError(" ${response.code()} ${response.message()}")
        } catch (exception: Exception) {
            return formateError(exception.message!! )
        }
    }

    private fun <T> formateError(errorMessage: String): ResultData<T> {
        return ResultData.failure("Network call has failed for a following reason: $errorMessage")
    }

}