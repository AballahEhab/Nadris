package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.CreatePostModel
import com.example.android.nadris.network.dtos.NetworkPost
import com.example.android.nadris.network.dtos.ProfileInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("api/Users/{id}")
    suspend fun getPublicProfileInfoById(@Path("id") id:String, @Header("authorization") token: String): Response<ProfileInfoDTO>

    @POST("api/Users/{id}/Follow")
    suspend fun followUser(@Path("id") userId:String, @Header("authorization") token: String): Response<Boolean>

    @GET("api/Users/{id}/posts")
    suspend fun getPostsByUserId(@Path("id") id:String, @Header("authorization") token: String): Response<List<NetworkPost>>

}