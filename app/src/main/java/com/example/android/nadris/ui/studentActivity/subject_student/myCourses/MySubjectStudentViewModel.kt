package com.example.android.nadris.ui.studentActivity.subject_student.myCourses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.database.models.DatabaseStudentSubject
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MySubjectStudentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var list = MutableLiveData<List<DatabaseStudentSubject>>()

    fun getdata() {
//        viewModelScope.launch {
//            val subjectFlow =
//                repository.getRegisteredCoursesForAStudent(TOKEN_PREFIX + NadrisApplication.firebaseUser?.Token)
//
//            subjectFlow.collect {
//                it.handleRepoResponse(
//                    onLoading = {
//
//                    },
//                    onError = {
//                        list.value = it.data!!
//                    },
//                    onSuccess = {
//                        list.value = it.data!!
//                    }
//                )
//            }
//        }
    }

    fun removeCourses(id: Long) {
//        viewModelScope.launch {
//            var result = repository.removeCourse(TOKEN_PREFIX + NadrisApplication.firebaseUser?.Token, id)
//            result.collect {
//                it.handleRepoResponse(
//                    onLoading = {},
//                    onError = {},
//                    onSuccess = {
//                        Log.v("comments responce", it.data.toString())
//                    }
//                )
//            }
//        }

    }
}