package com.example.android.nadris.repository

import com.example.android.nadris.network.CreateStudentAccountDataModelModel
import com.example.android.nadris.network.CreateTeacherAccountDataModelModel
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.asDataBaseModel
import com.example.android.nadris.util.postToApiHandler
import javax.inject.Inject


class Repository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

       fun login(loginAccountModel: LoginAccountModel)= postToApiHandler(
            request= { remoteDataSource.login(loginAccountModel) }
            ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToSaveModel = {networkModel ->  asDataBaseModel( networkModel  )}
        )

    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) = postToApiHandler(
        request= { remoteDataSource.createStudentAccount(accountDataModel) }
        ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToSaveModel = {networkModel ->  asDataBaseModel(networkModel)}
    )

    fun registerNewTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =  postToApiHandler(
        request= { remoteDataSource.createTeacherAccount(createTeacherAccountDataModelModel) }
        ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToSaveModel = {networkModel ->  asDataBaseModel(networkModel)}
    )
}











//fun <T, L> responseLiveData
//
//
//
//        (
//                roomQueryToRetrieveData: suspend () -> LiveData<T>,
//                networkRequest: suspend () -> ResultData<L>,
//                roomQueryToSaveData: suspend (L) -> Unit
//)
//
//
//
//
//: LiveData<ResultData<T>> =
//
//
//
//        liveData (Dispatchers.IO) {
//
//
//
//            emit(ResultData.loading(null))
//
//            val source = roomQueryToRetrieveData().map { ResultData.success(it) }
//
//            emitSource(source)
//            val responseStatus = networkRequest()
//            Log.v("responseStatus",responseStatus.toString())
//            when ( responseStatus) {
//                is ResultData.Success -> {
//                    roomQueryToSaveData(responseStatus.value)
//                }
//
//                is ResultData.Failure -> {
//                    emit(ResultData.failure(responseStatus.message))
//                    emitSource(source)
//                }
//
//                else -> {
//                    emit(ResultData.failure("Something went wrong, please try again later"))
//                    emitSource(source)
//                }
//            }
//
//        }