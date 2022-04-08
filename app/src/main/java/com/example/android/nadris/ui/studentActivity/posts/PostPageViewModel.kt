package com.example.android.nadris.ui.studentActivity.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.dtos.VoteModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@HiltViewModel
class PostPageViewModel @Inject constructor(val repository:Repository): ViewModel() {

    private var _navigate_to_add_post: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_add_post: MutableLiveData<Boolean> get() = _navigate_to_add_post

     var postsList =MutableLiveData< List<DatabasePost>>()


    private var _destinationProfileEmail = MutableLiveData<String?>(null)
    val destinationProfileEmail: MutableLiveData<String?> get() = _destinationProfileEmail

    private var _showIndicator :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator
    private var _loginRequestErrorMessage :MutableLiveData<String> =  MutableLiveData<String>("")
    val loginRequestErrorMessage get() = _loginRequestErrorMessage
    private var _errorMessageVisibility :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility
val  name=NadrisApplication.userData?.firstName+" "+NadrisApplication.userData?.lastName

    fun navigateToAddPost(){
        _navigate_to_add_post.value = true
    }

    fun navigateToAddPostDone(){
        _navigate_to_add_post.value = false
    }


    fun getPosts() {

        disableErrorMessage()
        enableProgressBar()
        viewModelScope.launch {
            val postsFlow = repository.getPosts( TOKEN_PREFIX + NadrisApplication.userData?.Token)
            postsFlow.collect {
                it.handleRepoResponse(
                    onLoading= {
                        it.data?.let{
                            disableProgressBar()
                            postsList.value= it
                        }
                    },
                    onError= {
                        disableProgressBar()
                        enableErrorMessage()
                        postsList.value= (it.data as List<DatabasePost>)
                        _loginRequestErrorMessage.value = it.error!!
                    },
                    onSuccess= {
                        disableProgressBar()
                        postsList.value= (it.data as List<DatabasePost>)
                        Log.v("posts responce", it.data.toString() )
                    },
                )

            }
        }

    }

    private fun enableErrorMessage(){
        _errorMessageVisibility.value = true

    }

    private fun disableErrorMessage(){
        _errorMessageVisibility.value = false
    }


    private fun enableProgressBar(){
        _showIndicator.value = true
    }

    private fun disableProgressBar(){
        _showIndicator.value = false
    }

    fun vote( postId: Long): DatabasePost? = runBlocking { async { voteToPost(postId) }.await() }


    private suspend fun voteToPost(postId: Long): DatabasePost? {
        var updatePost :DatabasePost? = null
        val postsFlow = repository.vote(VoteModel(postId),TOKEN_PREFIX + NadrisApplication.userData?.Token)
        postsFlow.collectLatest {
            it?.handleRepoResponse(
                onLoading= {

                },
                onError= {
                    it.data?.let{ post ->
                        updatePost = post
                    }
                },
                onSuccess= {
                    it.data?.let{ post ->
                        updatePost = post
                    }
                },
            )
        }
        return updatePost
    }

    fun BookMark(post:DatabasePost) {
        viewModelScope.launch {
            repository.updatePostById(post )
        }
    }

    fun navigateToPublicProfilePage(email: String) {
        _destinationProfileEmail.value = email
    }

    fun navigationToPublicProfileDone() {
        _destinationProfileEmail.value = null
    }
}