package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.ProfileInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {

    @GET("api/Profile/info")
    suspend fun  getProfileInfo( @Header("authorization") token: String ): Response<ProfileInfoDTO>

    //Response hold return from api


}