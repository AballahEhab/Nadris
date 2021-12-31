package com.example.android.nadris.repository

import com.example.android.nadris.network.AuthModel
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.NadrisAPIService
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(val localDataSource: LocalDataSource,val remoteDataSource: RemoteDataSource) {
    suspend fun Login(loginAccountModel:LoginAccountModel): Response<AuthModel> {
        return remoteDataSource.Login(loginAccountModel)
    }

}