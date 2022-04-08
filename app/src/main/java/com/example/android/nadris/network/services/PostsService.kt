package com.example.android.nadris.network.services

import com.example.android.nadris.network.dtos.*
import retrofit2.Response
import retrofit2.http.*

interface PostsService {
    @GET("api/Posts")
    suspend fun getAllPosts(@Header("authorization") token: String): Response<List<NetworkPost>>

    @POST("api/Posts")
    suspend fun publishAPost(@Body createPostModel: CreatePostModel, @Header("authorization") token: String): Response<NetworkPost>

    @GET("api/Posts/{id}")
    suspend fun getPostsByUserId(@Path("id") id:String, @Header("authorization") token: String): Response<CreatePostModel>

    @POST("api/Posts/vote")
    suspend fun vote(@Body voteModel: VoteModel, @Header("authorization") token: String) : Response<NetworkPost>

    @POST("api/Posts/comment")
    suspend fun addCommentToAPost(@Body publishCommentModel: PublishCommentModel, @Header("authorization") token: String) :Response<CommentModel>

    @GET("/api/Posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id:Long, @Header("authorization") token: String): Response<List<CommentModel>>

}