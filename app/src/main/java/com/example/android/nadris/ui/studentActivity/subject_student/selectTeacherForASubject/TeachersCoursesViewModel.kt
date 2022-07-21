package com.example.android.nadris.ui.studentActivity.subject_student.selectTeacherForASubject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.data.models.TeachersCoursesModel
import com.example.android.nadris.network.firebase.dtos.Course
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeachersCoursesViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    var list = MutableLiveData<List<TeachersCoursesModel>>()
    var coursesResultList = MutableLiveData<Result<List<Course>>>()
    var courseId: Long = 0
    var subjectId: String = ""

    var subscribeAStudentToACourseResult = MutableLiveData<Result<Boolean>>()
    var navigateToUnitsPageForCourse = MutableLiveData<String?>()


    /*var isJoin: Boolean = false
    val currentCourseData = MutableLiveData<TeachersCoursesDTO>()*/

    fun getCoursesWithSubject() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCoursesWithSubjectId(subjectId)
                .collect {
                    coursesResultList.postValue(it)
                }
        }
    }

    fun subscribeAStudentToACourse(selectedCourseID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.subScribeToACourse(selectedCourseID,NadrisApplication.currentDatabaseUser?.userID!!).collect{
                subscribeAStudentToACourseResult.postValue(it)
                navigateToUnitsPageForCourse.postValue(selectedCourseID)
            }

        }
    }


}

