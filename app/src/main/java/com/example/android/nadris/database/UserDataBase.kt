package com.example.android.nadris.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserData::class,PostData::class,CommentData::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun UserDao(): UserDao

}
