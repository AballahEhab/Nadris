package com.example.android.nadris.ui.studentActivity.addPosts

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.network.SubjectDTO
import com.example.android.nadris.network.dtos.CreatePostModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(val repository: Repository, @ApplicationContext val context: Context) :
    ViewModel() {
    var subjects: MutableLiveData<List<SubjectDTO>> = MutableLiveData<List<SubjectDTO>>()
    var selectedSubject: MutableLiveData<String> = MutableLiveData<String>()
    var question: MutableLiveData<String> = MutableLiveData<String>()
    var imageStrB64: String? = null

    fun addPost() {
        val token = NadrisApplication.userData?.Token
        val subjectId = subjects.value!!.find { it.name == selectedSubject.value }!!.id

        viewModelScope.launch {
            var res = repository.publishPost(
                CreatePostModel(subjectId, question.value!!, imageStrB64), TOKEN_PREFIX + token)
            res.collect {
                it?.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        Log.v("post", it.error.toString())
                    },
                    onSuccess = {
                        Log.v("post", it.data.toString())
                    },
                )
            }
        }
    }

    fun getSubjects() {
        val gradeId = NadrisApplication.userData!!.GradeId!!
        val token = NadrisApplication.userData!!.Token
        viewModelScope.launch {
            var res = repository.getGradeSubjects(gradeId, token)
            res.collect {
                it.handleRepoResponse(
                    onLoading = {
                    },
                    onError = {
                    },
                    onSuccess = {
                        subjects.value = it.data
                    },
                )

            }
        }
    }
}