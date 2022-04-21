package com.example.android.nadris.ui.studentActivity.posts.replies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.dtos.CommentModel
import com.example.android.nadris.network.dtos.PublishCommentModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCommentViewModel @Inject constructor(val repo: Repository) : ViewModel() {

    val currentPostData = MutableLiveData<DatabasePost>()
    var postId: Long = 0
    var commentsList = MutableLiveData<List<CommentModel>>()
    var comment = MutableLiveData<String>()
    var sendButtonVisabilty = MutableLiveData(false)

    //TODO:you should loading spinner and error message as a snack bar
     fun getAllComments() {
//    disableErrorMessage()
//    enableProgressBar()

        viewModelScope.launch{
            val commentsFlow = repo.getAllComments(TOKEN_PREFIX + NadrisApplication.userData?.Token,
                postId)
            commentsFlow.collect {
                it.handleRepoResponse(
                    onLoading = {
                        it.data?.let {
//                        disableProgressBar()
                        }
                    },
                    onError = {
//                    disableProgressBar()
//                    enableErrorMessage()
//                    _loginRequestErrorMessage.value = it.error!!
                    },
                    onSuccess = {
//                    disableProgressBar()
                        commentsList.value = (it.data as List<CommentModel>)
                        Log.v("comments responce", it.data.toString())
                    },
                )

            }
        }

    }


    fun getCurrentPost() {
//    disableErrorMessage()
//    enableProgressBar()
        viewModelScope.launch {
            val postFlow = repo.getPostById(postId)
            postFlow.collect {
                currentPostData.value = it
            }
        }
    }


    fun sendComment() {
        viewModelScope.launch {
            repo.publishComment(PublishCommentModel(comment.value!!,
                currentPostData.value?.postId!!),TOKEN_PREFIX + NadrisApplication.userData?.Token).collect()
            getAllComments()
        }
        comment.value = ""
    }


}