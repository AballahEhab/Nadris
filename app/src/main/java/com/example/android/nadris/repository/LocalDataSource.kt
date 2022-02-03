package com.example.android.nadris.repository

import com.example.android.nadris.database.UserData
import com.example.android.nadris.database.UserDataBase
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDataBase: UserDataBase) {

    private val dao = userDataBase.UserDao()

     suspend fun addUserData(userData:UserData)=
        dao.insertUser(userData)

    fun getUserData() = dao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) =
        dao.updateUser(updatedUserData)
}