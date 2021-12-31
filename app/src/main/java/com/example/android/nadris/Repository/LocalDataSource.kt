package com.example.android.nadris.repository

import com.example.android.nadris.database.UserData
import com.example.android.nadris.database.UserDataBase
import javax.inject.Inject

class LocalDataSource @Inject constructor(val userDataBase: UserDataBase) {

    val dao = userDataBase.UserDao

    suspend fun addUserData(userData:UserData){
        dao.insert(userData)
    }
}