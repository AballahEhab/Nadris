package com.example.android.nadris.network.dtos

data class SubjectDTO(
    val id: Long,
    val name: String,
    val section: String,
    val grade: String,
)

data class TeacherSubjectDTO(
    val id: Long,
    val name: String,
    val section: String,
    val term: String,
    val grade: String,
    val teacherName: String,
)

data class AddSubjectDTO(
    val subjectId: Long,
    val term: Int,
)

data class UnitLessonsDTO(
    val lessonId: Long,
    val name: String,
)

data class StudentSubjectDTO(
    val id: Long,
    val name: String,
    val grade: String,
    val section: String,
    val term: String,
    val teacherName: String,
    val progress: Long,
    val rate: Long,
)
