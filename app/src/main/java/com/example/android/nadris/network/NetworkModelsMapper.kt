package com.example.android.nadris.network

import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.network.dtos.AuthModel
import com.example.android.nadris.network.dtos.NetworkPost

object NetworkModelsMapper {
    fun postAsDatabaseModel(networkPost: NetworkPost) =
        DatabasePost(
            networkPost.id ,
            R.drawable.ic_google ,
            networkPost.subjectId ,
            networkPost.content ,
            networkPost.votes ,
            0, // TODO:  review post network model with @shokry
            networkPost.time ,
            networkPost.email ,
            networkPost.name,
        )

    fun authModelAsDataBaseModel(model: AuthModel) = UserData(
        Email = model.email,
        firstName = model.email,
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