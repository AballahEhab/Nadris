package com.example.android.nadris.ui.teacherActivity.myCourses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabaseTeacherSubject
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SubjectViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val isTeacher = NadrisApplication.currentDatabaseUser?.IsATeacher
    val navigateToAddingSectionFragmentEvent = MutableLiveData(false)
    var list = MutableLiveData<List<DatabaseTeacherSubject>>()

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