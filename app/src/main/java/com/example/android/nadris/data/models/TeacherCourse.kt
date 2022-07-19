package com.example.android.nadris.data.models

data class TeachersCoursesDTO(
    val id: Long,
    val name: String,
    val grade: String,
    val section: String,
    val term: String,
    val teacherName: String,
    val teacherId:String,
    val NumOfStudents:Long,
    val teacherProfileImageB64:String,
    val isJoined: Boolean,
)