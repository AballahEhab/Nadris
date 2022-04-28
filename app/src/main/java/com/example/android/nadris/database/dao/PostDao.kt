package com.example.android.nadris.database.dao

import androidx.room.*
import com.example.android.nadris.database.models.DatabasePost
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM DatabasePost")
    suspend fun getAllPosts(): List<DatabasePost>

    @Query("SELECT * FROM DatabasePost WHERE postId = :postIdP ")
    fun getPostByPostId(postIdP:Long): Flow<DatabasePost>

    @Query("SELECT postId FROM DatabasePost ")
    suspend fun getSavedDiscussionsIds(): List<Long>

    @Update
    suspend fun updateAllSavedDiscussions(discussions:List<DatabasePost>)

    @Update
    suspend fun updatePost(databasePost: DatabasePost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(databasePosts: List<DatabasePost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(databasePost: DatabasePost)

    @Query("DELETE FROM DatabasePost WHERE postId = :postIdP")
    suspend fun deleteDiscussion(postIdP:Long)

}