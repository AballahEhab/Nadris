package com.example.android.nadris.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(

    val Email: String,
    val firstName: String,
    val lastName: String,
    val PhoneNumber: String,
    val Type: String?,
    val Gender: Byte,
    val Exp: Long,
    val GradeId: Int?,
    val University: String?,
    val College: String?,
    val Token: String,
    @PrimaryKey val id:Int=1,
)

