package com.example.android.nadris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.nadris.database.dao.PostDao
import com.example.android.nadris.database.dao.UserDao
import com.example.android.nadris.database.models.CommentData
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData


@Database(entities = [UserData::class, DatabasePost::class, CommentData::class], version = 1, exportSchema = false)
abstract class NadrisDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao
    abstract fun PostDao(): PostDao

}
