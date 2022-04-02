package com.example.android.nadris.diModules

import com.example.android.nadris.network.services.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NetworkServicesModule {
    @Singleton
    @Provides
    fun provideSubjectsService(retrofitObj:Retrofit) = retrofitObj.create( SubjectsService::class.java)

    @Singleton
    @Provides
    fun provideUniversityService(retrofitObj:Retrofit) = retrofitObj.create( UniversityService::class.java)
    @Singleton
    @Provides
    fun provideGradesService(retrofitObj:Retrofit) = retrofitObj.create( GradesService::class.java)

    @Singleton
    @Provides
    fun provideUserService( retrofitObj:Retrofit) = retrofitObj.create(UserService::class.java)

    @Singleton
    @Provides
    fun providePostsService(retrofitObj:Retrofit) = retrofitObj.create(PostsService::class.java)

}