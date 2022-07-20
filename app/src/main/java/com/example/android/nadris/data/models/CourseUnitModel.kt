package com.example.android.nadris.data.models

data class CourseUnitModel(
    val unitId: Long,
    val subjectId: Long,
    val name: String,
    val term: String,
    val lessons: List<LessonDTO>,
)