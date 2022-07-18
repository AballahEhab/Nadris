package com.example.android.nadris.ui.studentActivity.subject_student.selectTeacherForASubject

import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeachersCoursesViewModel @Inject constructor(val repository: Repository) : ViewModel() {
//     var list = MutableLiveData<List<TeachersCoursesDTO>>()
//    val currentCourseData = MutableLiveData<TeachersCoursesDTO>()
    var subjectId: Long = 0
    var courseId: Long = 0
    var isJoin: Boolean = false

    fun getdata() {
//        viewModelScope.launch {
//            val subjectFlow =
//                repository.getTeachersCourses(subjectId,
//                    TOKEN_PREFIX + NadrisApplication.userData?.Token)
//            subjectFlow.collect {
//                it.handleRepoResponse(
//                    onLoading = {},
//                    onSuccess = {
//                        list.value = (it.data)
//                    },
//                    onError = {
//                        list.value = (it.data)
//                    },
//                )
//            }
//        }

    }

    fun registerAStudentInACourse(id : Long){
//        viewModelScope.launch {
//            var result=repository.registerAStudentInACourse(CourseID(id),TOKEN_PREFIX + NadrisApplication.userData?.Token)
//            result.collect{
//
//               it.handleRepoResponse(
//                   onLoading= {
//
//                   },
//                   onError= {
//                       Log.v("comments responce", it.data.toString())
//                   },
//                   onSuccess= {
//
//                   },
//               )
//            }
//        }
    }

}

