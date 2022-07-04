package com.example.android.nadris.ui.studentActivity.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class PostPageViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val TAG = "PostPageViewModel"

    private var _navigate_to_add_post: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_add_post: MutableLiveData<Boolean> get() = _navigate_to_add_post

    var postsList = MutableLiveData<List<Inquiry>>()
    val postsIsRefreshing = MutableLiveData(false)

    val aPostToEdit: MutableLiveData<DatabasePost> = MutableLiveData()

    private var _destinationProfileEmail = MutableLiveData<String?>(null)
    val destinationProfileEmail: MutableLiveData<String?> get() = _destinationProfileEmail

    private var _showIndicator: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _loginRequestErrorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val loginRequestErrorMessage get() = _loginRequestErrorMessage

    private var _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility

    val name =
        NadrisApplication.currentUserLocalData?.firstName + " " + NadrisApplication.currentUserLocalData?.lastName


    fun navigateToAddPost() {
        _navigate_to_add_post.value = true
    }

    fun navigateToAddPostDone() {
        _navigate_to_add_post.value = false
    }

    fun getPosts() {

        disableErrorMessage()
        enableProgressBar()
        viewModelScope.launch(Dispatchers.IO) {
            postsIsRefreshing.postValue(true)
            val result = repository.getAllInquiries()
            postsIsRefreshing.postValue(false)
            result.handleRepoResponse(
                onLoading = {},
                onError = {
                    Log.v(TAG, result.error.toString())
                },
                onSuccess = {
                    postsList.postValue(result.data!!)
                    Log.v(TAG, result.data.toString())
                }
            )
        }
    }

    //TODO: please enable bookmarking
    fun bookMark(discussion: DatabasePost) {
    }

    private fun enableErrorMessage() {
        _errorMessageVisibility.value = true
    }

    private fun disableErrorMessage() {
        _errorMessageVisibility.value = false
    }

    private fun enableProgressBar() {
        _showIndicator.value = true
    }

    private fun disableProgressBar() {
        _showIndicator.value = false
    }

    fun vote(postId: Long): DatabasePost? = runBlocking { async { voteToPost(postId) }.await() }

    private suspend fun voteToPost(postId: Long): DatabasePost? {
        var updatePost: DatabasePost? = null
//        val postsFlow =
//            repository.vote(VoteModel(postId), TOKEN_PREFIX + NadrisApplication.userData?.Token)
//        postsFlow.collectLatest {
//            it?.handleRepoResponse(
//                onLoading = {
//
//                },
//                onError = {
//                    it.data?.let { post ->
//                        updatePost = post
//                    }
//                },
//                onSuccess = {
//                    it.data?.let { post ->
//                        updatePost = post
//                    }
//                },
//            )
//        }
        return updatePost
    }

    fun navigateToPublicProfilePage(email: String) {
        _destinationProfileEmail.value = email
    }

    fun navigationToPublicProfileDone() {
        _destinationProfileEmail.value = null
    }

    fun deletepost(discussionId: Long) {
//        viewModelScope.launch {
//             val isDiscusisonDeleted = repository.deleteDiscussion(discussionId,TOKEN_PREFIX + NadrisApplication.userData?.Token)
//            if(isDiscusisonDeleted)
//                this@PostPageViewModel.getPosts()
//
//        }
    }

    fun navigate_to_edit_post(post: DatabasePost) {
        aPostToEdit.value = post
    }
}