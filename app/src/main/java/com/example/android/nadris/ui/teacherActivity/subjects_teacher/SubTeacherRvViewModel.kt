package com.example.android.nadris.ui.teacherActivity.subjects_teacher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.TeacherSubject
import com.example.android.nadris.network.dtos.TeacherSubjectDTO
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dataRvsubTeach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SubTeacherRvViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val navigateToAddingSectionFragmentEvent = MutableLiveData(false)
    var list = MutableLiveData<List<TeacherSubject>>()

    fun getdata() {
        viewModelScope.launch {
            val subjectFlow =
                repository.getTeacherSubject(TOKEN_PREFIX + NadrisApplication.userData?.Token)

            subjectFlow.collect {
                it.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        list.value = it.data!!
                    },
                    onSuccess = {
                        list.value = it.data!!
                    }
                )
            }
        }
    }

    fun navigateToAddingSectionFragment() {
        navigateToAddingSectionFragmentEvent.value = true
    }

    fun navigateToAddingSectionFragmentDone() {
        navigateToAddingSectionFragmentEvent.value = false
    }

}