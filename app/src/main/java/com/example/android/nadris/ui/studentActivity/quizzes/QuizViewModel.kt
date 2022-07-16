package com.example.android.nadris.ui.studentActivity.quizzes

import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.ui.teacherActivity.quiz_teacher.QuizData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class QuizViewModel:ViewModel() {
     var score:Int=0
     var currentPosition:Int=1
     var questionList:MutableList<QuizData> ? = null
     var selecedOption: MutableList<Int> = mutableListOf()
     var wrongQuestion : MutableList<Int> = mutableListOf()

}