package com.example.android.nadris.repository

import com.example.android.nadris.database.NadrisDatabase
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val nadrisDatabase: NadrisDatabase) {

    private val userDao = nadrisDatabase.UserDao()

    private val postDao = nadrisDatabase.PostDao()

     suspend fun addUserData(userData: UserData)= userDao.insertUser(userData)

    suspend fun getUserData() = userDao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) = userDao.updateUser(updatedUserData)

    suspend fun getAllPosts() =  postDao.getAllPosts()

    fun getPostById(postId:Long) = postDao.getPostByPostId(postId)

    suspend fun insertPosts(databasePosts: List<DatabasePost>) = postDao.insertPosts( databasePosts )

    suspend fun insertPost(databasePosts: DatabasePost) = postDao.insertPost( databasePosts )

    suspend fun updatePost(databasePosts: DatabasePost) = postDao.updatePost( databasePosts )


}