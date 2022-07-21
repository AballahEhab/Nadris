package com.example.android.nadris.ui.studentActivity.subject_student.myCourses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabaseStudentCourse
import com.example.android.nadris.network.firebase.dtos.Course
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MySubjectStudentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var list = MutableLiveData<List<DatabaseStudentCourse>>()
    var coursesResultList = MutableLiveData<Result<List<Course>>>()

    fun getCoursesCurrentUserSubscribedTo() {
        viewModelScope.launch(Dispatchers.IO) {
            val coursesSubscribedIds = NadrisApplication.currentUserData?.coursesSubscribedIds!!
            if(!coursesSubscribedIds.isNullOrEmpty())
                repository.getCurrentUserSubscribedCourses(coursesSubscribedIds)
                    .collect {
                        coursesResultList.postValue(it)
                    }
        }
    }
    fun unsubscribeFromACourse(id: Long) {
        viewModelScope.launch {
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
        }

    }
}