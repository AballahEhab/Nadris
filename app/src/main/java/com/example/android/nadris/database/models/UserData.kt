package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey val id:Int=1,
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
){
    fun getFullName() = firstName+lastName
}

