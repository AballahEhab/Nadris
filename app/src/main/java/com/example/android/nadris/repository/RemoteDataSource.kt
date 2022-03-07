package com.example.android.nadris.repository

import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.network.services.PostsService
import com.example.android.nadris.network.services.ProfileService
import com.example.android.nadris.network.services.SubjectsService
import com.example.android.nadris.network.services.UserService
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(private val usersService: UserService,
            private val postsService: PostsService,
            private val subjectsService: SubjectsService,
            private val profileService:ProfileService
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

    suspend fun vote(voteModel: VoteModel, token:String) =
        postsService.vote(voteModel,token)

    suspend fun comment(publishCommentModel: PublishCommentModel, token:String) =
        postsService.comment(publishCommentModel,token)

    suspend fun getCommentsByPostId(postId:Long,token:String) =
        postsService.getCommentByPostId(postId,token)

    suspend fun getGradeSubjects(gradeId: Long, token: String) =
        subjectsService.getGradeSubjects(gradeId, token)


    suspend fun getProfileInfo(token:String)=
        profileService.getProfileInfo(token)
}