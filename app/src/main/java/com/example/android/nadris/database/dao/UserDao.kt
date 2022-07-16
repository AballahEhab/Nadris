package com.example.android.nadris.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.nadris.database.models.DatabaseUser

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUser(databaseUser: DatabaseUser)

    @Update
     suspend fun updateUser(databaseUser: DatabaseUser)

    @Query("SELECT * FROM DatabaseUser WHERE dbId = 1")
     suspend fun getUser(): DatabaseUser

     @Delete
    suspend fun deleteUser(databaseUser:DatabaseUser)

}