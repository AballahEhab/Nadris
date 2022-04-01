package com.example.android.nadris.ui.studentActivity.units

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.TOKEN_PREFIX
import com.example.android.nadris.database.models.Lesson
import com.example.android.nadris.database.models.SubjectUnit
import com.example.android.nadris.network.NetworkModelsMapper
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitsViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    var list = MutableLiveData<List<SubjectUnit>>()
    var unitLessons = MutableLiveData<MutableList<Lesson>>()
    var subjectId: Long = 0
   lateinit var lessons:MutableLiveData<List<Lesson>>
    fun getdata() {
        viewModelScope.launch {
            val unitFlow = repository.getSubjectUnit(subjectId, TOKEN_PREFIX + NadrisApplication.userData?.Token)

            unitFlow.collect {
                it.handleRepoResponse(
                    onLoading = {},
                    onError = {

                    },
                    onSuccess = {
                        list.value = (it.data as List<NetworkModelsMapper.mapper>?)?.map { mapper -> mapper.unit

                        }
                        unitLessons.value?.addAll( (it.data )?.map { mapper -> mapper.lessons[0] } as Collection<Lesson>)

                    }
                )
            }
        }
    }


  suspend  fun getLessons(unitId: Long) = viewModelScope.launch {lessons.value=  repository.getUnitLessons(unitId)}
}
