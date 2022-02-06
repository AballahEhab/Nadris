package com.example.android.nadris.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface NadrisAPIService{

    @POST("api/Auth/Token")
    suspend fun login( @Body loginAccountData: LoginAccountModel): Response<AuthModel>  // send email and password and receive token as return string

    @POST("api/Auth/Register/Student")
    suspend fun createStudentAccount(@Body studentAccountDataModel: CreateStudentAccountDataModelModel ):Response<AuthModel>

    @POST("api/Auth/Register/Teacher")
    suspend fun createTeacherAccount(@Body TeacherAccountDataModel: CreateTeacherAccountDataModelModel ):Response<AuthModel>


    // todo: add token as an input to the header of the request
    @GET("api/Posts")
    suspend fun getAllPosts(@Header("authorization") token: String): Response<PostModel>

    @POST("api/Posts")
    suspend fun publishAPost(@Body postModel: PostModel ,@Header("authorization") token: String):Response<AuthModel>

    @GET("api/Posts/{postId}")
    suspend fun getAPostByPostId(@Body postId:Int,@Header("authorization") token: String):Response<PostModel>

    @GET("api/Posts/email")
    suspend fun getPostsByEmail(@Body email:String,@Header("authorization") token: String):Response<PostModel>

    @POST("api//Posts/Vote")
    suspend fun vote(@Body voteModel: VoteModel,@Header("authorization") token: String)

    @POST("api//Posts/comment")
    suspend fun comment(@Body commentModel: CommentModel,@Header("authorization") token: String)

    @GET("api/Posts/GetComment")
    suspend fun getCommentByPostId(@Body postId:Int,@Header("authorization") token: String):Response<CommentModel>
}
