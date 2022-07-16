package com.example.android.nadris.database.models

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.nadris.R


@Entity
data class DatabaseUser(
    @PrimaryKey val dbId: Int = 1,  // todo: to be updated to delete the default value
    val userID:String,
    val Email: String,
    val firstName: String,
    val lastName: String,
    val PhoneNumber: String,
    val IsATeacher: Boolean,
    val Gender: Boolean,
    val GradeId: String?,
    val UniversityName: String? = null,
    val CollegeName: String? = null,
) {
    fun getFullName() = "$firstName  $lastName"

    fun getUserType(context: Context): String {
        val resourceId = if (this.IsATeacher)
            R.string.teacher
        else
            R.string.student
    return context.resources.getString(resourceId)
    }

    override fun toString(): String {
        return "UserData(dbId=$dbId, userID='$userID', Email='$Email', firstName='$firstName', lastName='$lastName', PhoneNumber='$PhoneNumber', Type=$IsATeacher, Gender=$Gender, GradeId=$GradeId)"
    }

}

