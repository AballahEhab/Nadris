package com.example.android.nadris.network.dtos

data class CreateStudentAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val sectionId: Long
)