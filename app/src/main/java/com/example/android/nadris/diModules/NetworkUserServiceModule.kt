package com.example.android.nadris.diModules

import com.example.android.nadris.network.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkUserServiceModule {

    @Singleton
    @Provides
    fun provideUserService( retrofitObj:Retrofit) = retrofitObj.create(UserService::class.java)

}