package com.example.android.nadris.repository

import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.network.services.*
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(
    private val authService: AuthService,
    private val coursesService: CoursesService,
    private val gradesService: GradesService,
    private val postsService: PostsService,
    private val profileService: ProfileService,
    private val subjectsService: SubjectsService,
    private val universityService: UniversityService,
    private val usersService: UserService

    ) {

    suspend fun login(loginModel: LoginAccountModel) =
        authService.login(loginModel)

    suspend fun createStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        authService.createStudentAccount(accountDataModel)

    suspend fun createTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        authService.createTeacherAccount(createTeacherAccountDataModelModel)


    suspend fun getAllPosts(token: String) =
        postsService.getAllPosts(token)

    suspend fun publishAPost(createPostModel: CreatePostModel, token: String) =
        postsService.publishAPost(createPostModel, token)


    suspend fun getPostsByEmail(email: String, token: String) =
        postsService.getPostsByUserId(email, token)

    suspend fun vote(voteModel: VoteModel, token: String) =
        postsService.vote(voteModel, token)

    suspend fun comment(publishCommentModel: PublishCommentModel, token: String) =
        postsService.addCommentToAPost(publishCommentModel, token)

    suspend fun getCommentsByPostId(postId: Long, token: String) =
        postsService.getCommentsByPostId(postId, token)

    suspend fun getPostsByUserId( userId: String, token: String) =
        usersService.getPostsByUserId(userId, token)

    suspend fun getPublicProfileInfo(token: String,userId:String) =
        usersService.getPublicProfileInfoById(userId,token)

    suspend fun followProfile(token: String,userId:String) =
        usersService.followUser(userId,token)

    suspend fun addSubject(token: String, addSubjectDTO: AddSubjectDTO) =
        coursesService.addSubjectForATeacher(addSubjectDTO, token)

    suspend fun getTeacherSubjects(token: String) =
        coursesService.getTeacherCourses(token)

    suspend fun getGradeSubjects(gradeId: Long, token: String) =
        subjectsService.getSubjectsWithGradeId(gradeId, token)

    suspend fun getUniversities() =
        universityService.getUniversities()

    suspend fun getColleges(id: Int) =
        universityService.getCollegesWithUniversityId(id)

    suspend fun getSections() = gradesService.getAllGradesWithSections()

    suspend fun getGrades() = gradesService.getAllGrades()

    suspend fun getProfileInfo(token: String) =
        profileService.getCurrentUserProfileInfo(token)

    suspend fun getLastActivity(token: String) =
        profileService.getCurrentUserPosts(token)

    fun revokeToken(token:String) {
        authService.revokeToken(token)
    }


}