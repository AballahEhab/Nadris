package com.example.android.nadris.network.dtos

data class AuthModel(
    val message: String?,
    val isAuthenticated: Boolean,
    val firstName:String,
    val lastName:String,
    val email: String,
    val phoneNumber: String,
    val type: String?,
    val gender: Byte,
    val exp: Long,
    val gradeId: Int?,
    val university: String?,
    val college: String?,
    val roles:List<String>,
    val token: String,
    val expiresOn: String,
)