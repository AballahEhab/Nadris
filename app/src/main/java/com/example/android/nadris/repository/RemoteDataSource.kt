package com.example.android.nadris.repository

import com.example.android.nadris.network.CreateStudentAccountDataModelModel
import com.example.android.nadris.network.CreateTeacherAccountDataModelModel
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.NadrisAPIService
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(private val apiService: NadrisAPIService)  {

    suspend fun login(loginModel: LoginAccountModel) =
             apiService.login(loginModel)

    suspend fun createStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        apiService.createStudentAccount(accountDataModel)

    suspend fun createTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        apiService.createTeacherAccount(createTeacherAccountDataModelModel)

}