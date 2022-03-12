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

    @POST("api/Posts/vote")
    suspend fun vote(@Body voteModel: VoteModel, @Header("authorization") token: String) : Response<NetworkPost>

    @POST("api/Posts/comment")
    suspend fun comment(@Body publishCommentModel: PublishCommentModel, @Header("authorization") token: String) :Response<CommentModel>

    @GET("/api/Posts/{id}/comments")
    suspend fun getCommentByPostId(@Path("id") id:Long, @Header("authorization") token: String): Response<List<CommentModel>>

    @GET("api/Posts/MyPosts")
    suspend fun getLastActivity(@Header("authorization") token: String):Response<List<NetworkPost>>


}