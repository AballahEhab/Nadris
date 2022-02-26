package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.CommentData
import com.example.android.nadris.database.models.DatabasePost
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM DatabasePost")
    suspend fun getAllPosts(): List<DatabasePost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(databasePosts: List<DatabasePost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(databasePost: DatabasePost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(vararg comment: CommentData)

    @Query("SELECT * FROM CommentData WHERE postId = :postIdP ")
    suspend fun getCommentsByPostId(postIdP:Int): List<CommentData>

    @Update
    suspend fun updatePost(databasePost: DatabasePost)
}