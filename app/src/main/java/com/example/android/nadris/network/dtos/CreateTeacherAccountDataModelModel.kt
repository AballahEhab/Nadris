package com.example.android.nadris.network.dtos

 class CreateTeacherAccountDataModelModel(
     firstName: String,
     lastName: String,
     email: String,
     password: String,
     phoneNumber: String,
     gender: Int,
    val university: String,
    val college: String,
)
    : CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)