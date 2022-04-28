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
    suspend fun getPostsById(@Path("id") id:Long, @Header("authorization") token: String): Response<CreatePostModel>

    @PUT("api/Posts/{id}")
    suspend fun updateDiscussion(@Path("id") discussionID:Long, @Header("authorization") token: String,updatedDiscussion:CreatePostModel): Response<CreatePostModel> // TODO: edit the response type

    @DELETE("api/Posts/{id}")
    suspend fun deleteDiscussion(@Path("id") discussionID:Long, @Header("authorization") token: String): Response<Boolean>

    @POST("api/Posts/vote")
    suspend fun vote(@Body voteModel: VoteModel, @Header("authorization") token: String) : Response<NetworkPost>

    @POST("api/Posts/{id}/comment")
    suspend fun addCommentToAPost(@Body publishCommentModel: PublishCommentModel,@Path("id")postId:Long, @Header("authorization") token: String) :Response<CommentModel>

    @GET("api/Posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id:Long, @Header("authorization") token: String): Response<List<CommentModel>>

}