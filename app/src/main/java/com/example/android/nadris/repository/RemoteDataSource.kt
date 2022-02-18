package com.example.android.nadris.repository

import com.example.android.nadris.network.*
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(private val apiService: NadrisAPIService)  {

    suspend fun login(loginModel: LoginAccountModel) =
             apiService.login(loginModel)

    suspend fun createStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        apiService.createStudentAccount(accountDataModel)

    suspend fun createTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        apiService.createTeacherAccount(createTeacherAccountDataModelModel)

    suspend fun getAllPosts(token:String) =
        apiService.getAllPosts(token)

    suspend fun publishAPost(createPostModel: CreatePostModel,token:String) =
        apiService.publishAPost(createPostModel,token)

    suspend fun getAPostByPostId(postId:Int,token:String) =
        apiService.getAPostByPostId(postId,token)

    suspend fun getPostsByEmail(email:String,token:String) =
        apiService.getPostsByEmail(email,token)

    suspend fun vote(voteModel: VoteModel,token:String) =
        apiService.vote(voteModel,token)

    suspend fun comment(commentModel: CommentModel,token:String) =
        apiService.comment(commentModel,token)

    suspend fun getCommentsByPostId(postId:Int,token:String) =
        apiService.getCommentByPostId(postId,token)



}