package com.example.android.nadris.repository

import com.example.android.nadris.network.*
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(private val usersService: UserService,
            private val postsService: PostsService,
            private val subjectsService: SubjectsService
            ){

    suspend fun login(loginModel: LoginAccountModel) =
             usersService.login(loginModel)

    suspend fun createStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        usersService.createStudentAccount(accountDataModel)

    suspend fun createTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        usersService.createTeacherAccount(createTeacherAccountDataModelModel)

    suspend fun getAllPosts(token:String) =
        postsService.getAllPosts(token)

    suspend fun publishAPost(createPostModel: CreatePostModel,token:String) =
        postsService.publishAPost(createPostModel,token)

    suspend fun getAPostByPostId(postId:Int,token:String) =
        postsService.getAPostByPostId(postId,token)

    suspend fun getPostsByEmail(email:String,token:String) =
        postsService.getPostsByEmail(email,token)

    suspend fun vote(voteModel: VoteModel,token:String) =
        postsService.vote(voteModel,token)

    suspend fun comment(commentModel: CommentModel,token:String) =
        postsService.comment(commentModel,token)

    suspend fun getCommentsByPostId(postId:Int,token:String) =
        postsService.getCommentByPostId(postId,token)



}