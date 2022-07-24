package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference

data class QuestionData(
    var questionId: Int,
    var question: String,
    val answer: MutableList<String>,
    var correct_ans: MutableList<Int>,
    var answer_location: String,
)

data class QuizData(
    var quizId:String = "",
    var gradeRef: DocumentReference? = null,
    var subjectId: String = "",
    var ownerTeacherId: String = "",
    var quizTitle: String = "",
    var questions:List<QuestionData>,
    var term:Boolean = false
)
