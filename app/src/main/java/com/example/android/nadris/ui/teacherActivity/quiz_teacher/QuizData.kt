package com.example.android.nadris.ui.teacherActivity.quiz_teacher

data class QuizData(
    var id: Int,
    var question: String,
    val answer: MutableList<String>,
    var correct_ans: MutableList<Int>,
)