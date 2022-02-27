package com.example.android.nadris.repository

import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.database.NadrisDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()
    private val postDao = nadrisDatabase.PostDao()
    private val subjectDao = nadrisDatabase.SubjectDao()

     suspend fun addUserData(userData: UserData)=
        userDao.insertUser(userData)

    suspend fun getUserData() =
        userDao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) =
        userDao.updateUser(updatedUserData)

     fun getAllPosts() =
        postDao.getAllPosts()

    suspend fun insertPost(databasePosts: List<DatabasePost>) =
        postDao.insertPosts( databasePosts )


}