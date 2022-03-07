package com.example.android.nadris.repository

import com.example.android.nadris.network.*
import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.util.postToApiAndSaveToDatabase
import com.example.android.nadris.util.getFromApiAndSaveToDataBase
import com.example.android.nadris.util.requestAPI
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun login(loginAccountModel: LoginAccountModel) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.login(loginAccountModel) },
        saveFetchResult = { user -> localDataSource.addUserData(user) },
        convertToDatabaseModel = { networkModel -> NetworkModelsMapper.authModelAsDataBaseModel(networkModel) }
    )

    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.createStudentAccount(accountDataModel) },
        saveFetchResult = { user -> localDataSource.addUserData(user) },
        convertToDatabaseModel = { networkModel -> NetworkModelsMapper.authModelAsDataBaseModel(networkModel) }
    )

    fun registerNewTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        postToApiAndSaveToDatabase(
        request= { remoteDataSource.createTeacherAccount(createTeacherAccountDataModelModel) }
        ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToDatabaseModel = { networkModel ->  NetworkModelsMapper.authModelAsDataBaseModel(networkModel)}
    )

    suspend fun getUser() = localDataSource.getUserData()

    fun getPosts(token:String) = getFromApiAndSaveToDataBase(
        query = { localDataSource.getAllPosts() },
        fetch = { remoteDataSource.getAllPosts(token) },
        convertToDatabaseModel = { networkPostsList ->
            networkPostsList.map { networkPost -> NetworkModelsMapper.postAsDatabaseModel(networkPost) }
        },
        saveFetchResult = { list_of_posts -> list_of_posts?.let { localDataSource.insertPosts(it) } }
    )

    fun publishPost(post: CreatePostModel, token: String) = postToApiAndSaveToDatabase(
        request = { remoteDataSource.publishAPost(post, token) },
        convertToDatabaseModel = { networkPost -> NetworkModelsMapper.postAsDatabaseModel(networkPost) },
        saveFetchResult = { post -> localDataSource.insertPost(post) }
    )

    fun getGradeSubjects(gradeId: Long, token: String) = requestAPI(
        fetch={ remoteDataSource.getGradeSubjects(gradeId, token)}
    )

    fun getAllComments(token: String, postId: Long) = requestAPI(
        fetch = { remoteDataSource.getCommentsByPostId(postId,token) } )

    fun vote(voteModel: VoteModel, token:String) =
        postToApiAndSaveToDatabase(
            request= { remoteDataSource.vote(voteModel, token) }
            ,saveFetchResult= {post-> localDataSource.updatePost(post) }
            , convertToDatabaseModel = { networkMPost ->  NetworkModelsMapper.postAsDatabaseModel(networkMPost)}
        )

        suspend fun publishComment(comment: PublishCommentModel, token: String) = requestAPI (
            fetch = { remoteDataSource.comment(comment,token)}
            )

     fun getProfileInfo(token:String)= requestAPI  (
         fetch = {remoteDataSource.getProfileInfo(token)}

             )




}