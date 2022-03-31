package com.example.android.nadris.ui.teacherActivity.subjects_teacher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.TeacherSubject
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SubjectViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val isTeacher = NadrisApplication.userData?.isTeacher()

    var list = MutableLiveData<List<TeacherSubject>>()

    fun getdata() {
        viewModelScope.launch {
            val subjectFlow = repository.getTeacherSubject(TOKEN_PREFIX + NadrisApplication.userData?.Token)

            subjectFlow.collect {
                it.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        list.value=it.data!!
                    },
                    onSuccess = {
                        list.value=it.data!!
                    }
                )
            }
            }
        }


    }