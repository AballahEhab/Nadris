package com.example.android.nadris.ui.teacherActivity.addingQuiz

import androidx.lifecycle.ViewModel
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfQuizzesViewModel @Inject constructor(val repository: Repository) : ViewModel(){
    val isTeacher = NadrisApplication.currentDatabaseUser?.IsATeacher
}