package com.example.android.nadris.ui.studentActivity.headlineSubject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.UnitLessons
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineSubjectsViewModel @Inject constructor(val repository: Repository) : ViewModel(){
    val units = MutableLiveData<List<UnitLessons>>()
    var subjectId: Long = 0
    fun getData() {
        viewModelScope.launch {
            val unitFlow = repository.getSubjectUnit(subjectId, TOKEN_PREFIX + NadrisApplication.userData?.Token)

            unitFlow.collect {
                it.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        units.value = it.data!!
                    },
                    onSuccess = {
                        units.value = it.data!!
                    })
            }
        }
    }
}
