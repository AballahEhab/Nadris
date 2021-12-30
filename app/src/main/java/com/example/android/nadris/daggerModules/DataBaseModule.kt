package com.example.android.nadris.daggerModules

import android.content.Context
import androidx.room.Room
import com.example.android.nadris.database.UserDataBase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule(val applicationContext: Context) {


    private lateinit var  INSTANCE: UserDataBase

    @Provides
    fun provideDataBase(): UserDataBase {

        synchronized(this) {

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    applicationContext,
                    UserDataBase::class.java,
                    "user_data_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}