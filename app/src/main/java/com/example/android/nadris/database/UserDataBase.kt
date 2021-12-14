package com.example.android.nadris.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserData::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

}