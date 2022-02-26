package com.example.android.nadris.network.models

data class CreateStudentAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val sectionId: Int = 1
)
    : CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)