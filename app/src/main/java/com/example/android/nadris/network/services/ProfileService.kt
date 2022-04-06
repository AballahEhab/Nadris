package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.ProfileInfoDTO
import com.example.android.nadris.network.dtos.PublicProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {

    @GET("api/Profile/info")
    suspend fun  getProfileInfo( @Header("authorization") token: String ): Response<ProfileInfoDTO>


    //TODO: please change the end point path
    @GET("api/Profile/info")
    suspend fun  getPublicProfileInfo( @Header("authorization") token: String ,@Body email:String): Response<PublicProfileModel>

    //Response hold return from api


}