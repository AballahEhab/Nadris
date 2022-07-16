package com.example.android.nadris.ui.studentActivity.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostPageViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val TAG = "PostPageViewModel"

    private val _navigateToAddPost: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigateToAddPost: MutableLiveData<Boolean> get() = _navigateToAddPost

    private val _postsList = MutableLiveData<List<DatabasePost>>()
    val postsList :LiveData<List<DatabasePost>> get() = _postsList

    private var _updatedPostResult : MutableLiveData<Result<DatabasePost?>> = MutableLiveData()
    val updatedPostResult : LiveData<Result<DatabasePost?>> get() = _updatedPostResult

    private val _postsResults = MutableLiveData<Result<List<DatabasePost>>>()
    val postsResults: LiveData<Result<List<DatabasePost>>> get() = _postsResults

    private val _aPostToEdit: MutableLiveData<DatabasePost> = MutableLiveData()
    val aPostToEdit: LiveData<DatabasePost> get() = _aPostToEdit

    private var _destinationProfileEmail = MutableLiveData<String?>(null)
    val destinationProfileEmail: MutableLiveData<String?> get() = _destinationProfileEmail

    val name =
        NadrisApplication.currentDatabaseUser?.firstName + " " + NadrisApplication.currentDatabaseUser?.lastName

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllInquiries().collect {
                _postsResults.postValue(it)
            }
        }
    }

     fun voteToPost(postId: String) {

        val userID = NadrisApplication.currentDatabaseUser?.userID

        viewModelScope.launch(Dispatchers.IO){
            _updatedPostResult.postValue(repository.vote(userID, postId))
        }
    }

    //TODO: please enable bookmarking
    fun bookMark(discussion: DatabasePost) {

    }


    fun deletePost(discussionId: String) {
//        viewModelScope.launch {
//             val isDiscusisonDeleted = repository.deleteDiscussion(discussionId,TOKEN_PREFIX + NadrisApplication.userData?.Token)
//            if(isDiscusisonDeleted)
//                this@PostPageViewModel.getPosts()
//
//        }
    }

    fun updatePostsList(list:List<DatabasePost>){
        _postsList.value = list
    }

    fun updateASpecificPost(post:DatabasePost){
        _postsList.value = _postsList.value?.map {
            if(it.postId == post.postId)
                post
            else
                it
        }
    }

    fun navigateToAddPost() {
        _navigateToAddPost.value = true
    }

    fun navigateToAddPostDone() {
        _navigateToAddPost.value = false
    }

    fun navigateToPublicProfilePage(email: String) {
        _destinationProfileEmail.value = email
    }

    fun navigationToPublicProfileDone() {
        _destinationProfileEmail.value = null
    }

    fun navigateToEditPost(post: DatabasePost) {
        _aPostToEdit.value = post
    }

    fun navigationToEditPostDone() {
        _aPostToEdit.value = null
    }
}

