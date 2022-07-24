package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class QuestionData(
    @get:Exclude var questionId: Int = 0,
    var question: String = "",
    val answer: MutableList<String> = mutableListOf() ,
    var correct_ans: MutableList<Int> = mutableListOf(),
    var answer_location: String = "",
)

data class QuizData(
    var quizId:String = "",
    var gradeRef: DocumentReference? = null,
    @get:Exclude var gradeName: String = "",
    var subjectId: String = "",
    @get:Exclude var subjectName: String = "",
    var ownerTeacherId: String = "",
    @get:Exclude var teacherName: String = "",
    var quizTitle: String = "",
    var questions:List<QuestionData> = listOf(),
    var term:Boolean = false
)
