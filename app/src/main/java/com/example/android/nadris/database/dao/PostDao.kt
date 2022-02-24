package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.CommentData
import com.example.android.nadris.database.models.DatabasePost
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM DatabasePost")
    fun getAllPosts(): Flow<List<DatabasePost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(databasePosts: List<DatabasePost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(vararg comment: CommentData)

    @Query("SELECT * FROM CommentData WHERE postId = :postIdP ")
    fun getCommentsByPostId(postIdP:Int): Flow<List<CommentData>>

    @Update
    suspend fun updatePost(databasePost: DatabasePost)
}