package com.example.android.nadris.ui.studentActivity.addPosts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddPostViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    var subjects: MutableLiveData<String> = MutableLiveData<String>()
    var question: MutableLiveData<String> = MutableLiveData<String>()
    var IMAGE_REQUEST_CODE = 456
    var READ_STORAGE_PERM = 123
    var image: Int = 0
    fun gaddPost(token: String) {
        NadrisApplication.userData?.Token
        viewModelScope.launch {
            //   enableProgressBar()
//            var res = repository.publishPost(
//                CreatePostModel(subjects.value.toString(), question.value!!), "Bearer " + token)
//
//            res.collect {
//                it?.handleRepoResponse(
//                    onLoading = {},
//                    onError = {
////                        disableProgressBar()
////                        enableErrorMessage()
////                        _loginRequestErrorMessage.value = it.error!!
//                    },
//                    onSuccess = {
//                        // disableProgressBar()
//                        //   postsList.value= (it.data as List<DatabasePost>)
//                        Log.v("posts responce", it.data.toString())
//                    },
//                )
//            }
        }
    }
}