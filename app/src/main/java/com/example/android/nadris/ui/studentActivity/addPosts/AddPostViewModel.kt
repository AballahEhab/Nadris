package com.example.android.nadris.ui.studentActivity.addPosts

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.NadrisApplication.Companion.currentUserLocalData
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(val repository: Repository, @ApplicationContext val context: Context) :
    ViewModel() {
    var editedDiscussionId:Long? = null
    var navigateBackToHomeScreen:MutableLiveData<Boolean> =MutableLiveData(false)
//    var subjects: MutableLiveData<List<SubjectDTO>> = MutableLiveData<List<SubjectDTO>>()
//    var grades: MutableLiveData<List<GradeDTO>> = MutableLiveData<List<GradeDTO>>()
    var selectedSubject: MutableLiveData<String> = MutableLiveData<String>()
    var question: MutableLiveData<String> = MutableLiveData<String>()
    var imageStrB64: String? = null
    val isTeacher = currentUserLocalData!!.Type
    var gradeId = if (isTeacher) 0 else currentUserLocalData!!.GradeId!!
    private var selectedGrade: MutableLiveData<String> = MutableLiveData<String>()


    fun setSelectedGrade(grade: String) {
        selectedGrade.value = grade
        getSubjects()
    }

    fun getSelectedGrade() = selectedGrade.value

    fun addPost() {
//        val token = firebaseUser?.Token
//        val subjectId = subjects.value!!.find { it.name == selectedSubject.value }!!.id

//        viewModelScope.launch {
//            var res = repository.publishPost(
//                CreatePostModel(subjectId, question.value!!, imageStrB64), TOKEN_PREFIX + token)
//            res.collect {
//                it?.handleRepoResponse(
//                    onLoading = {},
//                    onError = {
//                        Log.v("post", it.error.toString())
//                    },
//                    onSuccess = {
//                        Log.v("post", it.data.toString())
//                    },
//                )
//            }
//        }
    }

    //todo:update this fun to edit dicussion
    fun updatePostAfterEdit() {
//        val token = firebaseUser?.Token
//        val subjectId = subjects.value!!.find { it.name == selectedSubject.value }!!.id

//        viewModelScope.launch {
//            var res = repository.updateDiscussion(editedDiscussionId!!,
//                EditDiscussion(question.value!!,subjectId,/* imageStrB64*/), TOKEN_PREFIX + token)
//            res.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    },
//                    onError = {
//                        Log.v("post", it.error.toString())
//                    },
//                    onSuccess = {
//                        navigateBackToHomeScreen()
//                    },
//                )
//            }
//        }
    }

    fun getGrades() {
        if (isTeacher) {
//            viewModelScope.launch {
//                var res = repository.getGrades()
//                res.collect {
//                    it.handleRepoResponse(
//                        onLoading = {
//                        },
//                        onError = {
//                        },
//                        onSuccess = {
//                            grades.value = it.data!!
//                        },
//                    )
//                }
//            }
        }
    }

    fun getSubjects() {
//        if (isTeacher) gradeId = grades.value!!.find { it.name == selectedGrade.value }!!.id
//        val token = firebaseUser!!.Token
//        viewModelScope.launch {
//            var res = repository.getGradeSubjects(gradeId, token)
//            res.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//                    },
//                    onError = {
//                    },
//                    onSuccess = {
//                        subjects.value = it.data!!
//                    },
//                )
//
//            }
//        }
    }

    fun navigateBackToHomeScreen() {
        navigateBackToHomeScreen.value = true
    }

    fun navigationBackToHomeScreenDone() {
        navigateBackToHomeScreen.value = false

    }


}