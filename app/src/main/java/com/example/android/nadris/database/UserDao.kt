package com.example.android.nadris.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserData)

    @Update
    suspend fun update(user: UserData)

    @Query("SELECT * FROM UserData WHERE email = :user_email")
    suspend fun get(user_email:String)

}