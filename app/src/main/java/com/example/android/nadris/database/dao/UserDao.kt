package com.example.android.nadris.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.nadris.database.models.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(user: UserData)

    @Update
     suspend fun updateUser(user: UserData)

    @Query("SELECT * FROM UserData WHERE id = 1")
     suspend fun getUser(): UserData

     @Delete
    suspend fun deleteUser(user:UserData)

}