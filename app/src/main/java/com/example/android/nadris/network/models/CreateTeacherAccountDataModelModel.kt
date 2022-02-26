package com.example.android.nadris.network.models

data class CreateTeacherAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val university: String,
    val college: String,
)
    : CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)