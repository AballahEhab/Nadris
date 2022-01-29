package com.example.android.nadris.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.android.nadris.database.UserData
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class Repository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

     fun login(loginAccountModel: LoginAccountModel): ResultData<UserData>? {
        val response = responseLiveData(
                {localDataSource.getUserData()},
                {remoteDataSource.get(loginAccountModel)},
                { localDataSource.addUserData(it.asDataBaseModel())}

            )

        return response.value
    }
}

fun <T, L> responseLiveData



        (
                roomQueryToRetrieveData: suspend () -> LiveData<T>,
                networkRequest: suspend () -> ResultData<L>,
                roomQueryToSaveData: suspend (L) -> Unit
)




: LiveData<ResultData<T>> =



        liveData (Dispatchers.IO) {



            emit(ResultData.loading(null))

            val source = roomQueryToRetrieveData().map { ResultData.success(it) }

            emitSource(source)
            val responseStatus = networkRequest()
            Log.v("responseStatus",responseStatus.toString())
            when ( responseStatus) {
                is ResultData.Success -> {
                    roomQueryToSaveData(responseStatus.value)
                }

                is ResultData.Failure -> {
                    emit(ResultData.failure(responseStatus.message))
                    emitSource(source)
                }

                else -> {
                    emit(ResultData.failure("Something went wrong, please try again later"))
                    emitSource(source)
                }
            }

        }