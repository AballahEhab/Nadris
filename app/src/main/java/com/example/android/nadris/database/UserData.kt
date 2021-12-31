package com.example.android.nadris.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter


@Entity
data class UserData(
    @PrimaryKey
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
    val id:Int=1,
)