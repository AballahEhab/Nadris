package com.example.android.nadris.ui.teacherActivity.addingQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.firebase.dtos.QuizData
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfQuizzesViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val isTeacher = NadrisApplication.currentDatabaseUser?.IsATeacher

    private val navigateToEditQuizFragment = MutableLiveData(false)

    var quizzesList = MutableLiveData<List<QuizData>>()

    var quizzesResultList = MutableLiveData<Result<List<QuizData?>>>()

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> get() = _loadingState

    fun getTeacherQuizzes() {
        enableLoading()
        viewModelScope.launch(Dispatchers.IO) {
            if(NadrisApplication.currentDatabaseUser?.IsATeacher!!)
            repository.getQuizzesWithTeacherId(NadrisApplication.currentDatabaseUser?.userID!!)
                .collect {
                    quizzesResultList.postValue(it)
                }
            else
                repository.getQuizzesWithGradeRef(NadrisApplication.currentUserData?.grade!!)
                    .collect {
                        quizzesResultList.postValue(it)
                    }
        }
    }


    fun navigateToAddingSectionFragment() {
        navigateToEditQuizFragment.value = true
    }

    fun navigateToAddingSectionFragmentDone() {
        navigateToEditQuizFragment.value = false
    }

    fun enableLoading() {
        _loadingState.value = true
    }

    fun disableLoading() {
        _loadingState.value = false
    }

}