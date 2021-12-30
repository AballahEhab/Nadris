package com.example.android.nadris.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase




@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract val UserDao: UserDao
}
