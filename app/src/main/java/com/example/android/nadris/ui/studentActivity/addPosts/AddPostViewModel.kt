package com.example.android.nadris.ui.studentActivity.addPosts

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication.Companion.currentUserLocalData
import com.example.android.nadris.network.firebase.dtos.Grade
import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.example.android.nadris.network.firebase.dtos.Subject
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    val repository: Repository,
    @ApplicationContext val context: Context,
) :
    ViewModel() {

    private val TAG = "AddPostViewModel"
    var editedDiscussionId: Long? = null
    var navigateBackToHomeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    var subjects: MutableLiveData<List<Subject>> = MutableLiveData<List<Subject>>()
    var grades: MutableLiveData<List<Grade>> = MutableLiveData<List<Grade>>()
    var selectedSubject: MutableLiveData<String> = MutableLiveData<String>()
    var question: MutableLiveData<String> = MutableLiveData<String>()
    var imageStrB64: String? = null
    var imageFile: File? = null

    val isTeacher = currentUserLocalData!!.Type
    private var selectedGrade: MutableLiveData<String> = MutableLiveData<String>()


    fun setSelectedGrade(grade: String) {
        selectedGrade.value = grade
        if (!grade.isNullOrEmpty()) getSubjects()
    }

    fun getSelectedGrade() = selectedGrade.value

    fun addPost() {
        val subjectRef = subjects.value!!.find { it.name_ar == selectedSubject.value }!!.docRef
        viewModelScope.launch(Dispatchers.IO) {
            val inquiry = Inquiry(
                body = question.value!!,
                subject = subjectRef,
                userID = currentUserLocalData?.userID!!)

            val result =
                if (imageFile == null)
                    repository.addNewInquiryWithoutImage(inquiry)
                else
                    repository.addNewInquiryWithImage(inquiry, imageFile!!)

            result.handleRepoResponse(
                onLoading = {},
                onError = {
                    Log.v(TAG, result.error.toString())
                },
                onSuccess = {
                    Log.v(TAG, result.data.toString())
                }
            )
        }
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
            viewModelScope.launch(Dispatchers.IO) {
                val result = repository.getGrades()
                result.handleRepoResponse(
                    onLoading = {
                    },
                    onError = {
                        Log.v("jaflsjfs", result.error.toString())
                    },
                    onSuccess = {
                        grades.postValue(result.data)
                        Log.v("jaflsjfs", result.data.toString())
                    }
                )
            }
        }
    }

    fun getSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            val gradeRef =
                if (isTeacher)
                    grades.value!!.find { it.name_ar == selectedGrade.value }!!.gradeReference!!
                else {
                    val result = repository.getUserData(currentUserLocalData?.userID!!)
                    (result.data as User).grade
                }
            val result = repository.getSubjectsWithGrade(gradeRef!!)
            result.handleRepoResponse(
                onLoading = {
                },
                onError = {
                    Log.v("AddPostViewModel", result.error!!)
                },
                onSuccess = {
                    subjects.postValue(result.data!!)
                },
            )
        }
    }

    fun navigateBackToHomeScreen() {
        navigateBackToHomeScreen.value = true
    }

    fun navigationBackToHomeScreenDone() {
        navigateBackToHomeScreen.value = false
    }


}