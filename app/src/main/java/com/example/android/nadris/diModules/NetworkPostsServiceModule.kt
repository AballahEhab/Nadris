package com.example.android.nadris.diModules

import com.example.android.nadris.network.PostsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkPostsServiceModule {
    @Inject
    lateinit var retrofitObj:Retrofit

    @Singleton
    @Provides
    fun provideUserService() = retrofitObj.create(PostsService::class.java)

}