package com.example.android.nadris.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: UserData)

    @Update
     suspend fun updateUser(user: UserData)

    @Query("SELECT * FROM UserData WHERE id = 1")
     fun getUser(): Flow<UserData>

    @Query("SELECT * FROM DatabasePost")
     fun getAllPosts():Flow<List<DatabasePost>>

    @Insert(onConflict = REPLACE)
    suspend fun insertPost(databasePosts: List<DatabasePost>)

    @Insert(onConflict = REPLACE)
    suspend fun insertComments(vararg comment: CommentData)

    @Query("SELECT * FROM CommentData WHERE postId = :postIdP ")
     fun getCommentsByPostId(postIdP:Int): Flow<List<CommentData>>

    @Update
    suspend fun updatePost(databasePost: DatabasePost)

}