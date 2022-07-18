package com.example.android.nadris.ui.studentActivity.subject_student.exploreCourses

import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubjectsRvFragmentViewModel @Inject constructor(val repository: Repository): ViewModel() {

//     var list=MutableLiveData<List<SubjectDTO>>()

    fun getData(){
//        viewModelScope.launch {
//            val subjectFlow =
//                repository.getSubjects(userData?.GradeId!!,TOKEN_PREFIX + userData?.Token)
//
//            subjectFlow.collect {
//                it.handleRepoResponse(
//                    onLoading = {},
//                    onSuccess = {
//                                list.value= (it.data)
//                    },
//                    onError = {
//                        list.value= (it.data)
//                    },
//                )
//            }
//        }

    }


    
}