package com.example.android.nadris.diModules

import android.content.Context
import androidx.room.Room
import com.example.android.nadris.database.NadrisDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule() {


    private lateinit var  INSTANCE: NadrisDatabase

    @Provides
    fun provideDataBase(@ApplicationContext  applicationContext: Context): NadrisDatabase {

        synchronized(this) {

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    applicationContext,
                    NadrisDatabase::class.java,
                    "user_data_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}