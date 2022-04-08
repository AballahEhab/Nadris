package com.example.android.nadris.network.dtos

data class UnitDTO(
    val unitId: Int ,
    val subjectId:Int ,
    val name:String,
    val term:String ,
    val lessons:List<LessonDTO>
)

data class LessonDTO(
    val lessonId:Int,
    val name:String
)


data class AddLessonDTO(
    val subjectId:Int,
    val lessonId:Int,
    val content:String
)