package com.example.android.nadris.repository

import com.example.android.nadris.network.*
import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.util.postToApiHandler
import com.example.android.nadris.util.requestDataFromAPI
import com.example.android.nadris.util.requestFromAPIOnly
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    fun login(loginAccountModel: LoginAccountModel) = postToApiHandler(
        request = { remoteDataSource.login(loginAccountModel) },
        saveFetchResult = { user -> localDataSource.addUserData(user) },
        convertToDatabaseModel = { networkModel -> NetworkModelsMapper.authModelAsDataBaseModel(networkModel) }
    )

    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) = postToApiHandler(
        request = { remoteDataSource.createStudentAccount(accountDataModel) },
        saveFetchResult = { user -> localDataSource.addUserData(user) },
        convertToDatabaseModel = { networkModel -> NetworkModelsMapper.authModelAsDataBaseModel(networkModel) }
    )

    fun registerNewTeacherAccount(createTeacherAccountDataModelModel: CreateTeacherAccountDataModelModel) =
        postToApiHandler(
        request= { remoteDataSource.createTeacherAccount(createTeacherAccountDataModelModel) }
        ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToDatabaseModel = { networkModel ->  NetworkModelsMapper.authModelAsDataBaseModel(networkModel)}
    )

    fun vote(voteModel: VoteModel, token:String) =
        postToApiHandler(
            request= { remoteDataSource.vote(voteModel, token) }
            ,saveFetchResult= {post-> localDataSource.updatePost(post) }
            , convertToDatabaseModel = { networkMPost ->  NetworkModelsMapper.postAsDatabaseModel(networkMPost)}
        )

    fun getPosts(token:String) = requestDataFromAPI(
        query = { localDataSource.getAllPosts() },
        fetch = { remoteDataSource.getAllPosts(token) },
        convertToDatabaseModel = { networkPostsList ->
            networkPostsList.map { networkPost -> NetworkModelsMapper.postAsDatabaseModel(networkPost) }
        },
        saveFetchResult = { list_of_posts -> list_of_posts?.let { localDataSource.insertPosts(it) } }
    )

    fun publishPost(post: CreatePostModel, token: String) = postToApiHandler(
        request = { remoteDataSource.publishAPost(post, token) },
        convertToDatabaseModel = { networkPost -> NetworkModelsMapper.postAsDatabaseModel(networkPost) },
        saveFetchResult = { post -> localDataSource.insertPost(post) }
    )

    suspend fun getUser() = localDataSource.getUserData()

    fun getGradeSubjects(gradeId: Long, token: String) = requestFromAPIOnly(
       fetch={ remoteDataSource.getGradeSubjects(gradeId, token)}
    )
    //    suspend fun publishComment(comment: PublishCommentModel, token: String) =
//        remoteDataSource.publishComment(comment,token)

    fun getAllComments(token: String, postId: Long) = requestFromAPIOnly(
        fetch = { remoteDataSource.getCommentsByPostId(postId,token) } )

}