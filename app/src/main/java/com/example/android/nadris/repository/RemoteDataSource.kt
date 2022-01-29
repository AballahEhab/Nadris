package com.example.android.nadris.repository

import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.NadrisAPIService
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
        private val apiService: NadrisAPIService) : BaseDataSource() {

    suspend fun get(loginModel: LoginAccountModel) =
            getData { apiService.login(loginModel) }

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
            return formateError(exception.message!!)
        }
    }

    private fun <T> formateError(errorMessage: String): ResultData<T> {
        return ResultData.failure("Network call has failed for a following reason: $errorMessage")
    }

}