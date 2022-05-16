package com.example.android.nadris.repository

import com.example.android.nadris.database.models.*
import com.example.android.nadris.network.NetworkModelsMapper
import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.util.*
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun login(loginAccountModel: LoginAccountModel) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.login(loginAccountModel) },
        saveFetchResult = { user -> localDataSource.addUserData(user) },
        convertToDatabaseModel = { networkModel ->
            NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
        }
    )

    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) =
        postToApiAndSaveToDatabase(
            request = { remoteDataSource.createStudentAccount(accountDataModel) },
            saveFetchResult = { user -> localDataSource.addUserData(user) },
            convertToDatabaseModel = { networkModel ->
                NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
            }
        )

    fun registerNewTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        postToApiAndSaveToDatabase(
            request = { remoteDataSource.createTeacherAccount(createTeacherAccountDataModelModel) },
            saveFetchResult = { user -> localDataSource.addUserData(user) },
            convertToDatabaseModel = { networkModel ->
                NetworkModelsMapper.authModelAsDataBaseModel(networkModel)
            }
        )

    suspend fun getUser() = localDataSource.getUserData()

    fun getPosts(token: String) = getFromApiAndSaveToDataBase(
        query = { localDataSource.getAllPosts() },
        fetch = { remoteDataSource.getAllPosts(token) },
        convertToDatabaseModel = { networkPostsList ->
            networkPostsList.map { networkPost ->
                NetworkModelsMapper.postAsDatabaseModel(networkPost)
            }
        },
        saveFetchResult = { list_of_posts -> list_of_posts.let { localDataSource.insertPosts(it) } }
    )

    suspend fun getSubjectUnit(subjectID: Long, token: String) = getFromApiAndSaveToDataBase(

        query = { localDataSource.getSubjectUnits(subjectID) },
        fetch = { remoteDataSource.getSubjectUnit(subjectID, token) },
        convertToDatabaseModel = { list ->
            list.map { item ->
                NetworkModelsMapper.subjectUnitsDTOtoModel(item)
            }
        },
        saveFetchResult = { list ->
            list.map {
                localDataSource.insertSubjectUnit(it.unit)
                localDataSource.insertUnitLessons(it.lessons)
            }
        }

    )

    suspend fun updatePostById(post: DatabasePost) = localDataSource.updatePost(post)

    suspend fun getPostById(postId: Long) = localDataSource.getPostById(postId)

    fun publishPost(post: CreatePostModel, token: String) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.publishAPost(post, token) },
        convertToDatabaseModel = { networkPost ->
            NetworkModelsMapper.postAsDatabaseModel(networkPost)
        },
        saveFetchResult = { post -> localDataSource.insertPost(post) }
    )

    fun getPostsByUserId(userID: String, token: String) = requestAPI(
        fetch = { remoteDataSource.getPostsByUserId(userID, token) }
    )

    fun getGradeSubjects(gradeId: Long, token: String) = requestAPI(
        fetch = { remoteDataSource.getSubjectsWithGradeId(gradeId, token) }
    )

    fun getAllComments(token: String, postId: Long) = requestAPI(
        fetch = { remoteDataSource.getCommentsByPostId(postId, token) })

    fun updateDiscussion(postId:Long,updatedDiscussion: EditDiscussion, token: String) = requestAPI(
        fetch = { remoteDataSource.updateDiscussion(postId,updatedDiscussion, token) })

    fun vote(voteModel: VoteModel, token: String) =
        postToApiAndSaveToDatabase(
            request = { remoteDataSource.vote(voteModel, token) },
            saveFetchResult = { post -> localDataSource.updatePost(post) },
            convertToDatabaseModel = { networkMPost ->
                NetworkModelsMapper.postAsDatabaseModel(networkMPost)
            }
        )

    suspend fun publishComment(comment: PublishCommentModel,postId:Long, token: String) = requestAPI(
        fetch = { remoteDataSource.comment(comment,postId, token) }
    )

    fun getUniversities() = requestAPI(
        fetch = { remoteDataSource.getUniversities() })

    fun getProfileInfo(token: String) = requestAPI(
        fetch = { remoteDataSource.getProfileInfo(token) })

    fun flowProfile(userID: String, token: String) = requestAPI(
        fetch = { remoteDataSource.followProfile(userID, token) })

    fun getPublicProfileInfo(token: String, userId: String) = requestAPI(
        fetch = { remoteDataSource.getPublicProfileInfo(token, userId) })

    fun getLastActivity(token: String) = requestAPI(
        fetch = { remoteDataSource.getLastActivity(token) })

    fun getColleges(id: Int) = requestAPI(
        fetch = { remoteDataSource.getColleges(id) })

    fun getSections() = requestAPI(
        fetch = { remoteDataSource.getSections() })

    fun getGrades() = requestAPI(
        fetch = { remoteDataSource.getGrades() })

    fun getTeacherSubject(token: String) = getFromApiAndSaveToDataBase(
        query = { localDataSource.getSubjects() },
        fetch = { remoteDataSource.getTeacherCourses(token) },
        convertToDatabaseModel = { list ->
            list.map { dto ->
                NetworkModelsMapper.subjectDTOtoModel(dto)
            }
        },
        saveFetchResult = { list -> list.let { localDataSource.insertSubjects(it) } }
    )

    fun getRegisteredCoursesForAStudent(token: String) = getFromApiAndSaveToDataBase(
        query = { localDataSource.getRegisteredCoursesForAStudent() },
        fetch = { remoteDataSource.getRegisteredCoursesForAStudent(token) },
        convertToDatabaseModel = { list -> list.map { dto -> NetworkModelsMapper.studentCourseDTOtoModel(dto) } },
        saveFetchResult = { list -> list.let { localDataSource.addRegisteredCoursesForAStudent(it as List<StudentSubject>) } }
    )

    fun addTeacherSubject(token: String, addSubjectDTO: AddSubjectDTO) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.addSubject(token, addSubjectDTO) },
        saveFetchResult = { item -> localDataSource.insertSubject(item) },
        convertToDatabaseModel = { item -> NetworkModelsMapper.subjectDTOtoModel(item) }


    )

    suspend fun logOut(user: UserData) = localDataSource.deleteUser(user)

    fun updateProfilePic(token: String, imgStrB64: UploadPhotoDTO) = requestAPI(
        fetch = { remoteDataSource.updateProfilePic(token,imgStrB64) })

    suspend fun getUnitLessons(unitId:Long)=localDataSource.getUnitLessons(unitId)


    suspend fun getUpdatedDiscussions(token:String): List<DatabasePost> {

        //getting saved posts ids from database to update them to the database from the fetched posts
        val savedDiscussionsIds = localDataSource.getSavedDiscussionsIds()

        lateinit var allDiscussions: List<DatabasePost>

        //getting all discussions from the api
        val allDiscussionsResponse = remoteDataSource.getAllPosts(token)

        // check if the request is successful
        if(allDiscussionsResponse.isSuccessful){

            //mapping network posts to database posts to be ready to be stored or shown for the user
             allDiscussions = allDiscussionsResponse.body()?.map { discussion ->
                 NetworkModelsMapper.postAsDatabaseModel(discussion)  }!!

            // setting the saved discussions parameter isBookmarked to true for the ids fetched from the database
            allDiscussions.map {discussion->
                if(savedDiscussionsIds.contains(discussion.postId))
                    discussion.isBookMarked = true
            }

            // filtering the saved discussions
            val savedDiscussions = allDiscussions.filter { discussion->  discussion.isBookMarked }


            // updating the database with the saved discussions data fetched from the api
             localDataSource.updateAllSavedDiscussions(savedDiscussions)
        }else{
            return localDataSource.getAllPosts()
        }

        return allDiscussions
    }

    suspend fun bookmarkDiscussion(discussion:DatabasePost){
        if(discussion.isBookMarked)
        localDataSource.insertPost(discussion)
        else
            localDataSource.deleteDiscussion(discussion.postId)
    }

    suspend fun deleteDiscussion(discussionId:Long,token:String): Boolean {
        val responce = remoteDataSource.deleteDiscussion(discussionId,token)
        if(responce.isSuccessful && responce.body()!!) {
            localDataSource.deleteDiscussion(discussionId)
            return true
        }
            return false
    }


}