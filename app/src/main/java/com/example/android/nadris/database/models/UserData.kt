package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserData(
    @PrimaryKey val dbId: Int = 1,  // todo: to be updated to delete the default value
    val userID:String,
    val Email: String,
    val firstName: String,
    val lastName: String,
    val PhoneNumber: String,
    val Type: Boolean,
    val Gender: Boolean,
    val GradeId: String?,
    val UniversityName: String? = null,
    val CollegeName: String? = null,
) {
    fun getFullName() = "$firstName  $lastName"
    fun getUserType() = if(this.Type) "Teacher" else "Student"
    override fun toString(): String {
        return "UserData(dbId=$dbId, userID='$userID', Email='$Email', firstName='$firstName', lastName='$lastName', PhoneNumber='$PhoneNumber', Type=$Type, Gender=$Gender, GradeId=$GradeId)"
    }

}

