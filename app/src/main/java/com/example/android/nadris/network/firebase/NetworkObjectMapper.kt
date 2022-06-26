package com.example.android.nadris.network.firebase

import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.network.firebase.dtos.User

object NetworkObjectMapper {
    fun userToDatabaseUser(user: User) =
        UserData(
            userID = user.id,
            Email= user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            PhoneNumber = user.phoneNumber,
            Type = user.type,
            Gender = user.gender,
            GradeId = user.grade?.id,
        )
}