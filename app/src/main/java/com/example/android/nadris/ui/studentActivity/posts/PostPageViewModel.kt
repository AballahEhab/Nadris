package com.example.android.nadris.ui.studentActivity.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.database.PostData
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostPageViewModel @Inject constructor(val repository:Repository): ViewModel() {

    private var _navigate_to_add_post: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_add_post: MutableLiveData<Boolean> get() = _navigate_to_add_post

     var postsList = mutableListOf<PostData>()

    private var _showIndicator :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator
    private var _loginRequestErrorMessage :MutableLiveData<String> =  MutableLiveData<String>("")
    val loginRequestErrorMessage get() = _loginRequestErrorMessage
    private var _errorMessageVisibility :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility


    fun navigate_to_add_post(){
        _navigate_to_add_post.value = true
    }

    fun navigate_to_add_post_done(){
        _navigate_to_add_post.value = false
    }


    fun getPosts(token:String) {
        disableErrorMessage()
        viewModelScope.launch {
            enableProgressBar()
            val postsFlow = repository.getPosts("Bearer " + token)

            postsFlow.collect {
                it?.handleRepoResponse(
                    onLoading= {},
                    onError= {
                        disableProgressBar()
                        enableErrorMessage()
                        _loginRequestErrorMessage.value = it.error!!
                    },
                    onSuccess= {
                        disableProgressBar()
                        postsList.addAll(it.data as Collection<PostData>)
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

}