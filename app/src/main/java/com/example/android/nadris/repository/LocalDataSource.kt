package com.example.android.nadris.repository

import com.example.android.nadris.database.models.CommentData
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.database.NadrisDatabase
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()
    private val postDao = nadrisDatabase.PostDao()

     suspend fun addUserData(userData: UserData)=
        userDao.insertUser(userData)

    suspend fun getUserData() = userDao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) =
        userDao.updateUser(updatedUserData)

     fun getAllPosts() =
        postDao.getAllPosts()

    suspend fun insertPosts(databasePosts: List<DatabasePost>) =
        postDao.insertPosts( databasePosts )
    suspend fun insertPost(databasePost: DatabasePost) =
        postDao.insertPost( databasePost )

    suspend fun insertComments(vararg comment: CommentData) =
        postDao.insertComments(*comment)

     fun getCommentsByPostId(postIdP:Int) =
        postDao.getCommentsByPostId(postIdP)

}