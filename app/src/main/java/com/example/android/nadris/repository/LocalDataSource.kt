package com.example.android.nadris.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.nadris.database.CommentData
import com.example.android.nadris.database.PostData
import com.example.android.nadris.database.UserData
import com.example.android.nadris.database.UserDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDataBase: UserDataBase) {

    private val dao = userDataBase.UserDao()

     suspend fun addUserData(userData:UserData)=
        dao.insertUser(userData)

    fun getUserData() = dao.getUser()

    suspend fun updateUserData(updatedUserData: UserData) =
        dao.updateUser(updatedUserData)

     fun getAllPosts() =
        dao.getAllPosts()

    suspend fun insertPost(post: List<PostData>) =
        dao.insertPost( post )

    suspend fun insertComments(vararg comment: CommentData) =
        dao.insertComments(*comment)

     fun getCommentsByPostId(postIdP:Int) =
        dao.getCommentsByPostId(postIdP)

}