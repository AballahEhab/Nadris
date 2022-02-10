package com.example.android.nadris.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.android.nadris.network.CommentModel
import com.example.android.nadris.network.VoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: UserData)

    @Update
     suspend fun updateUser(user: UserData)

    @Query("SELECT * FROM UserData WHERE id = 1")
     fun getUser(): Flow<UserData>

    @Query("SELECT * FROM PostData")
     fun getAllPosts():Flow<List<PostData>>

    @Insert(onConflict = REPLACE)
    suspend fun insertPost( posts: List<PostData>)

    @Insert(onConflict = REPLACE)
    suspend fun insertComments(vararg comment: CommentData)

    @Query("SELECT * FROM CommentData WHERE postId = :postIdP ")
     fun getCommentsByPostId(postIdP:Int): Flow<List<CommentData>>

    @Update
    suspend fun updatePost(post: PostData)

}