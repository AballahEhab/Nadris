package com.example.android.nadris.network.dtos

data class SubjectDTO(
    val id: Long,
    val name: String,
    val section: String,
    val grade: String,
    )
data class TeacherSubjectDTO(
    val id : Long,
    val name : String,
    val section : String,
    val term : String,
    val grade: String,
    val teacherName : String,
)
data class AddSubjectDTO(
    val subjectId: Long,
    val term: Int,
)
data class SubjectUnitDTO(
    val unitId:  Long,
    val name: String,
    val term : String,
    val lessons : List<UnitLessonsDTO>,
)
data class UnitLessonsDTO(
    val lessonId:Long,
    val name: String,
)
