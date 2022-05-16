package com.example.android.nadris.ui.studentActivity.subject_student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.NadrisApplication.Companion.userData
import com.example.android.nadris.R
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.DatabaseSubject
import com.example.android.nadris.database.models.Subjects
import com.example.android.nadris.network.dtos.SubjectDTO
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsRvFragmentViewModel @Inject constructor(val repository: Repository): ViewModel() {

     var list=MutableLiveData<List<SubjectDTO>>()

    fun getData(){
        viewModelScope.launch {
            val subjectFlow =
                repository.getSubjects(userData?.GradeId!!,TOKEN_PREFIX + userData?.Token)

            subjectFlow.collect {
                it.handleRepoResponse(
                    onLoading = {},
                    onSuccess = {
                                list.value= (it.data)
                    },
                    onError = {
                        list.value= (it.data)
                    },
                )
            }
        }

    }


    
}