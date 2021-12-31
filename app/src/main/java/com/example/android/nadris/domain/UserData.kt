package com.example.android.nadris.domain

import java.time.format.DateTimeFormatter

data class UserData(
    val Email: String,
    val firstName: String,
    val lastName: String,
    val PhoneNumber: String,
    val Type: String,
    val Gender: Byte,
    val Exp: Long,
    val Grade: Int,
    val University: String?,
    val College: String?,
    val Token: String,
    val ExpiresOn: DateTimeFormatter,
)

