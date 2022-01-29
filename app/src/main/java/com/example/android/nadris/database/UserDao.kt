package com.example.android.nadris.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(user: UserData)

    @Update
    suspend fun update(user: UserData)

    @Query("SELECT * FROM UserData WHERE id = 1")
     fun get(): LiveData<UserData>

}