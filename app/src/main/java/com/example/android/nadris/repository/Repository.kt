package com.example.android.nadris.repository

import com.example.android.nadris.network.NetworkModelsMapper
import com.example.android.nadris.network.dtos.*
import com.example.android.nadris.util.postToApiHandler
import com.example.android.nadris.util.requestDataFromAPI
import com.example.android.nadris.util.requestFromAPIOnly
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {


       fun login(loginAccountModel: LoginAccountModel)= postToApiHandler(
            request= { remoteDataSource.login(loginAccountModel) }
            ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToDatabaseModel = { networkModel ->  NetworkModelsMapper.authModelAsDataBaseModel( networkModel  )}
        )

    fun registerNewStudentAccount(accountDataModel: CreateStudentAccountDataModelModel) = postToApiHandler(
        request= { remoteDataSource.createStudentAccount(accountDataModel) }
        ,saveFetchResult= {user-> localDataSource.addUserData(user)}
        , convertToDatabaseModel = { networkModel ->  NetworkModelsMapper.authModelAsDataBaseModel(networkModel)}
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
        convertToDatabaseModel = { networkPostsList-> networkPostsList.map {
                networkPost-> NetworkModelsMapper.postAsDatabaseModel(networkPost) } },
        saveFetchResult = { list_of_posts-> list_of_posts?.let { localDataSource.insertPost(it) } }
    )

    suspend fun getUser() = localDataSource.getUserData()

//    suspend fun publishComment(comment: PublishCommentModel, token: String) =
//        remoteDataSource.publishComment(comment,token)

    fun getAllComments(token: String, postId: Long) = requestFromAPIOnly(
    fetch = { remoteDataSource.getCommentsByPostId(postId,token) } )
}












//fun <T, L> responseLiveData
//
//
//
//        (
//                roomQueryToRetrieveData: suspend () -> LiveData<T>,
//                networkRequest: suspend () -> ResultData<L>,
//                roomQueryToSaveData: suspend (L) -> Unit
//)
//
//
//
//
//: LiveData<ResultData<T>> =
//
//
//
//        liveData (Dispatchers.IO) {
//
//
//
//            emit(ResultData.loading(null))
//
//            val source = roomQueryToRetrieveData().map { ResultData.success(it) }
//
//            emitSource(source)
//            val responseStatus = networkRequest()
//            Log.v("responseStatus",responseStatus.toString())
//            when ( responseStatus) {
//                is ResultData.Success -> {
//                    roomQueryToSaveData(responseStatus.value)
//                }
//
//                is ResultData.Failure -> {
//                    emit(ResultData.failure(responseStatus.message))
//                    emitSource(source)
//                }
//
//                else -> {
//                    emit(ResultData.failure("Something went wrong, please try again later"))
//                    emitSource(source)
//                }
//            }
//
//        }