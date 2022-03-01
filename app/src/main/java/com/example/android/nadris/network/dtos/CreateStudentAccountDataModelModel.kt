package com.example.android.nadris.network.dtos

 class CreateStudentAccountDataModelModel(
     firstName: String,
     lastName: String,
     email: String,
     password: String,
     phoneNumber: String,
     gender: Int,
    val sectionId: Int = 1
)
    : CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)