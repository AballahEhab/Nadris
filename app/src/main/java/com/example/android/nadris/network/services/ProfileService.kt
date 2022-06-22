package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {

    @GET("api/Profile/info")
    suspend fun  getCurrentUserProfileInfo(@Header("authorization") token: String ): Response<ProfileInfoDTO>

    @GET("api/Profile/Posts")
    suspend fun getCurrentUserPosts(@Header("authorization") token: String):Response<List<NetworkPost>>

    @POST("api/Profile/profilePic")
    suspend fun updateProfilePic(@Header("authorization") token: String, @Body imgStrB64:UploadPhotoDTO):Response<UploadPhotoStatus>

    @PUT("api/Profile/EditPassword")
    suspend fun editPassword(@Header("authorization") token: String,@Body editPassword:EditPasswordModel)



}