package com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.firebase.dtos.Subject
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsRvFragmentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var subjectsList = MutableLiveData<List<Subject>>()
    var subjectResult = MutableLiveData<Result<List<Subject>>>()


    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val gradeRef = NadrisApplication.currentUserData?.grade
            val result = repository.getSubjectsWithGrade(gradeRef!!)
            subjectResult.postValue(result)
        }
    }
}