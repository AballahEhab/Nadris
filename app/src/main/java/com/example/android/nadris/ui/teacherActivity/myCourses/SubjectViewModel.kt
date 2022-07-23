package com.example.android.nadris.ui.teacherActivity.myCourses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabaseTeacherCourse
import com.example.android.nadris.network.firebase.dtos.Course
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SubjectViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val isTeacher = NadrisApplication.currentDatabaseUser?.IsATeacher
    private val navigateToAddingSectionFragmentEvent = MutableLiveData(false)
    var list = MutableLiveData<List<DatabaseTeacherCourse>>()
    var coursesResultList = MutableLiveData<Result<List<Course>>>()

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> get() = _loadingState

    fun getCoursesCurrentUserSubscribedTo() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrentUserSubscribedCourses(NadrisApplication.currentUserData?.myCourses!!)
                .collect {
                    coursesResultList.postValue(it)
                }
        }
    }


    fun navigateToAddingSectionFragment() {
        navigateToAddingSectionFragmentEvent.value = true
    }

    fun navigateToAddingSectionFragmentDone() {
        navigateToAddingSectionFragmentEvent.value = false
    }

    fun enableLoading() {
        _loadingState.value = true
    }

    fun disableLoading() {
        _loadingState.value = false
    }

}
