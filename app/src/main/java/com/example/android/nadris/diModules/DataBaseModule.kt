package com.example.android.nadris.diModules

import android.content.Context
import androidx.room.Room
import com.example.android.nadris.database.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule() {


    private lateinit var  INSTANCE: UserDataBase

    @Provides
    fun provideDataBase(@ApplicationContext  applicationContext: Context): UserDataBase {

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