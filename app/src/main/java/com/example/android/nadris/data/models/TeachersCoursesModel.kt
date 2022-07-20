package com.example.android.nadris.data.models

data class TeachersCoursesModel(
    val courseId: String,
    val subjectName: String,
    val gradeName: String,
    val teacherName: String,
    val teacherId:String,
    val numOfStudents:Int,
    val teacherImagePath:String,
    val isStudentJoined: Boolean,
)