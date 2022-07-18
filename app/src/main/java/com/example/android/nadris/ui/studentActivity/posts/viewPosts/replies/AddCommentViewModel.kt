package com.example.android.nadris.ui.studentActivity.posts.viewPosts.replies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.data.models.CommentModel
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.network.firebase.dtos.Reply
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCommentViewModel @Inject constructor(val repo: Repository) : ViewModel() {

    private val TAG = "AddCommentViewModel"

    val currentPostData = MutableLiveData<DatabasePost>()
    val postDataResult:MutableLiveData<Result<DatabasePost>> = MutableLiveData()
    var postId: String? = null
    val commentsList = MutableLiveData<List<CommentModel>>()
    val commentsResult:MutableLiveData<Result<List<CommentModel>>> = MutableLiveData()
    val newComment = MutableLiveData<String>("")
    val addingNewCommentResult:MutableLiveData<Result<Boolean>> = MutableLiveData()

     fun getAllComments() {
         viewModelScope.launch (Dispatchers.IO){
             repo.getReplies(postId!!).collect {
                 commentsResult.postValue(it)
             }
         }
    }

    fun getCurrentPost() {
        viewModelScope.launch (Dispatchers.IO){
            repo.getInquiryAsResultWithId(postId!!).collect {
                postDataResult.postValue(it)
            }
        }
    }

    fun sendComment() {
        viewModelScope.launch(Dispatchers.IO) {
             repo.addNewReply(Reply(
                 replyBody = newComment.value,
                 userId = NadrisApplication.currentDatabaseUser?.userID
             ),postId!!).collect {
                 addingNewCommentResult.postValue(it)
            }
        }
    }

    fun voteToPost() {
        Log.v(TAG,"vote pressed")
    }

    fun bookMark() {
        Log.v(TAG,"bookmark pressed")
    }


}