package com.example.android.nadris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.nadris.database.dao.PostDao
import com.example.android.nadris.database.dao.SubjectDao
import com.example.android.nadris.database.dao.UserDao
import com.example.android.nadris.database.models.*


@Database(entities = [UserData::class,
    DatabasePost::class,
    DatabaseSubject::class,
    Lesson::class,
    TeacherSubject::class,
    SubjectUnit::class],
    version = 1,
    exportSchema = false)
abstract class NadrisDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao
    abstract fun PostDao(): PostDao
    abstract fun SubjectDao(): SubjectDao


}
