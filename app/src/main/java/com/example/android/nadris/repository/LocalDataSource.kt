package com.example.android.nadris.repository

import com.example.android.nadris.database.CommentData
import com.example.android.nadris.database.DatabasePost
import com.example.android.nadris.database.UserData
import com.example.android.nadris.database.NadrisDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val dao = nadrisDatabase.UserDao()

     suspend fun addUserData(userData:UserData)=
        dao.insertUser(userData)

    fun getUserData() = dao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) =
        dao.updateUser(updatedUserData)

     fun getAllPosts() =
        dao.getAllPosts()

    suspend fun insertPost(databasePost: List<DatabasePost>) =
        dao.insertPost( databasePost )

    suspend fun insertComments(vararg comment: CommentData) =
        dao.insertComments(*comment)

     fun getCommentsByPostId(postIdP:Int) =
        dao.getCommentsByPostId(postIdP)

}