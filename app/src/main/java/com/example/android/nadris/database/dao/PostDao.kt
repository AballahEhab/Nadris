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

    @Update
    suspend fun updatePost(databasePost: DatabasePost)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(databasePosts: List<DatabasePost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(databasePost: DatabasePost)

}