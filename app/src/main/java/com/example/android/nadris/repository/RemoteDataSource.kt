package com.example.android.nadris.repository

import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.network.services.*
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(
    private val usersService: UserService,
    private val postsService: PostsService,
    private val subjectsService: SubjectsService,
    private val universityService: UniversityService,
    private val gradesService: GradesService,
    private val profileService: ProfileService,
) {

    suspend fun login(loginModel: LoginAccountModel) =
        usersService.login(loginModel)

    suspend fun createStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        usersService.createStudentAccount(accountDataModel)

    suspend fun createTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        usersService.createTeacherAccount(createTeacherAccountDataModelModel)

    suspend fun getAllPosts(token: String) =
        postsService.getAllPosts(token)

    suspend fun publishAPost(createPostModel: CreatePostModel, token: String) =
        postsService.publishAPost(createPostModel, token)

    suspend fun getAPostByPostId(postId: Int, token: String) =
        postsService.getAPostByPostId(postId, token)

    suspend fun getPostsByEmail(email: String, token: String) =
        postsService.getPostsByEmail(email, token)

    suspend fun vote(voteModel: VoteModel, token: String) =
        postsService.vote(voteModel, token)

    suspend fun comment(publishCommentModel: PublishCommentModel, token: String) =
        postsService.comment(publishCommentModel, token)

    suspend fun getCommentsByPostId(postId: Long, token: String) =
        postsService.getCommentByPostId(postId, token)

    suspend fun getTeacherSubjects(token: String) =
        subjectsService.getTeacherSubjects(token)

    suspend fun getGradeSubjects(gradeId: Long, token: String) =
        subjectsService.getGradeSubjects(gradeId, token)

    suspend fun getUniversities() =
        universityService.getUniversities()

    suspend fun getColleges(id: Int) =
        universityService.getColleges(id)

    suspend fun getSections() = gradesService.getSections()
    suspend fun getGrades() = gradesService.getGrades()
    suspend fun addSubject(token: String, addSubjectDTO: AddSubjectDTO) =
        subjectsService.addTeacherSubject(addSubjectDTO, token)

    suspend fun getProfileInfo(token: String) =
        profileService.getProfileInfo(token)

    suspend fun getPublicProfileInfo(token: String,userId:String) =
        profileService.getPublicProfileInfo(token,userId)

    suspend fun getLastActivity(token: String) =
        postsService.getLastActivity(token)


}