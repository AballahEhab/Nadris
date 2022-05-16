package com.example.android.nadris.diModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitObjProvider {

    @Singleton
    @Provides
    fun provideRetrofitObj() =
        Retrofit.Builder()
            .baseUrl("https://nadrisapi.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}