package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface PostsService {
    @GET("api/Posts")
    suspend fun getAllPosts(@Header("authorization") token: String): Response<List<NetworkPost>>

    @POST("api/Posts")
    suspend fun publishAPost(@Body createPostModel: CreatePostModel, @Header("authorization") token: String): Response<NetworkPost>

    @GET("api/Posts/{postId}")
    suspend fun getAPostByPostId(@Path("postId") postId:Int, @Header("authorization") token: String): Response<CreatePostModel>

    @GET("api/Posts/email")
    suspend fun getPostsByEmail(@Body email:String, @Header("authorization") token: String): Response<CreatePostModel>

    @POST("api//Posts/Vote")
    suspend fun vote(@Body voteModel: VoteModel, @Header("authorization") token: String)

    @POST("api//Posts/comment")
    suspend fun comment(@Body publishCommentModel: PublishCommentModel, @Header("authorization") token: String)

    @GET("api/Posts/GetComment")
    suspend fun getCommentByPostId(@Body postId:Int, @Header("authorization") token: String): Response<PublishCommentModel>
}