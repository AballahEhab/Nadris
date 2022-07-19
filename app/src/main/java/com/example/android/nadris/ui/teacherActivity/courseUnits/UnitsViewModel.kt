package com.example.android.nadris.ui.teacherActivity.courseUnits

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.database.models.DatabaseUnitLessons
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnitsViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val units = MutableLiveData<List<DatabaseUnitLessons>>()
    var subjectId: String = ""

    fun getData() {
//        viewModelScope.launch {
//            val unitFlow = repository.getSubjectUnit(subjectId, TOKEN_PREFIX + NadrisApplication.userData?.Token)
//
//            unitFlow.collect {
//                it.handleRepoResponse(
//                    onLoading = {},
//                    onError = {
//                        units.value = it.data!!
//                    },
//                    onSuccess = {
//                        units.value = it.data!!
//                    })
//            }
//        }
    }
}
