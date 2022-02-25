package com.example.android.nadris.diModules

import com.example.android.nadris.network.SubjectsService
import com.example.android.nadris.network.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkSubjectsServiceModule {

    @Singleton
    @Provides
    fun provideUserService(retrofitObj:Retrofit) = retrofitObj.create( SubjectsService::class.java)


}