package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey val id: Int = 1,  // todo: to be updated to delete the default value
    val Email: String,
    val firstName: String,
    val lastName: String,
    val PhoneNumber: String,
    val Type: String?,
    val Gender: Byte,
    val Exp: Long,
    val GradeId: Long?,
    val University: String?,
    val College: String?,
    val Token: String,
) {
    fun getFullName() = "$firstName  $lastName"
    fun isTeacher(): Boolean = Type == "teacher"

}

