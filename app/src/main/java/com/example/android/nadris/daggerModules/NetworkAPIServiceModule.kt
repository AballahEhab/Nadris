package com.example.android.nadris.daggerModules

import com.example.android.nadris.network.NadrisAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkAPIServiceModule {
@Provides
fun provideNetworkAPIService():NadrisAPIService
=  Retrofit.Builder()
        .baseUrl("https://nadris-api.conveyor.cloud/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NadrisAPIService::class.java)

}