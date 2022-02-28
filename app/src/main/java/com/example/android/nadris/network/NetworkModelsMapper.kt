package com.example.android.nadris.network

import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.services.Converter

object NetworkModelsMapper {
    fun postAsDatabaseModel(networkPost: NetworkPost): DatabasePost {
        var hasImage = false
        if (!networkPost.imgStrB64.isNullOrEmpty()) {
            NadrisApplication.instance?.let {
                Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.imgStrB64,
                    networkPost.id.toString())
            }
            hasImage = true
        }
        return DatabasePost(
            networkPost.id,
            hasImage,
            networkPost.subject,
            networkPost.content,
            networkPost.votes,
            networkPost.time,
            networkPost.email,
            networkPost.name,
        )
    }

    fun authModelAsDataBaseModel(model: AuthModel) = UserData(
        Email = model.email,
        firstName = model.firstName,
        lastName = model.lastName,
        PhoneNumber = model.phoneNumber,
        Type = model.type,
        Gender = model.gender,
        Exp = model.exp,
        GradeId = model.gradeId,
        University = model.university,
        College = model.college,
        Token = model.token
    )


}