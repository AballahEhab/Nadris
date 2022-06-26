package com.example.android.nadris.ui.studentActivity.quizzes

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
     var score: Int = 0
     var currentPosition: Int = 1
     var questionList: ArrayList<QuestionData>? = null
     var selecedOption: Int = 0





}