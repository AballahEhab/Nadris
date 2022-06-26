package com.example.android.nadris.ui.teacherActivity.subjects_teacher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.TeacherSubject
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SubjectViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val isTeacher = NadrisApplication.currentUserLocalData?.Type
    val navigateToAddingSectionFragmentEvent = MutableLiveData(false)
    var list = MutableLiveData<List<TeacherSubject>>()

    fun getdata() {
//        viewModelScope.launch {
//            val subjectFlow =
//                repository.getTeacherSubject(TOKEN_PREFIX + NadrisApplication.userData?.Token)
//
//            subjectFlow.collect {
//                it.handleRepoResponse(
//                    onLoading = {},
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

    /** todo:to be used on clicking on lesson
    fun navigateToAddingSectionFragment() {
        navigateToAddingSectionFragmentEvent.value = true
    }

    fun navigateToAddingSectionFragmentDone() {
        navigateToAddingSectionFragmentEvent.value = false
    }
**/
    }