package com.example.android.nadris.diModules

import com.example.android.nadris.network.services.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkProfileServiceModel {

    @Singleton
    @Provides
    fun provideProfileService(retrofitObj: Retrofit) = retrofitObj.create(ProfileService::class.java)
}