package com.example.android.nadris.ui.teacherActivity.courseUnits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.database.models.DatabaseCourseUnitWithLessonsAndSections
import com.example.android.nadris.network.firebase.dtos.Unit
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnitsViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val unitsList = MutableLiveData<List<DatabaseCourseUnitWithLessonsAndSections>>()
    val unitsListResult = MutableLiveData<Result<List<Unit>>>()
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> get() = _loadingState

    var courseId: String = ""

    fun getCourseUnits() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCourseUnit(courseId).collect {
                unitsListResult.postValue(it)
            }
        }
    }

     fun enableLoading() {
        _loadingState.value = true
    }
     fun disableLoading() {
        _loadingState.value = false
    }
}
