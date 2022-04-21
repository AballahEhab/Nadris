package com.example.android.nadris.network.dtos

data class

UnitDTO(
    val unitId: Long ,
    val subjectId:Long ,
    val name:String,
    val term:String ,
    val lessons:List<LessonDTO>
)

data class LessonDTO(
    val lessonId:Long,
    val name:String
)


data class AddLessonDTO(
    val subjectId:Long,
    val lessonId:Long,
    val content:String
)