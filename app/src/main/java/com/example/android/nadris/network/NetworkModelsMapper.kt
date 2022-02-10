package com.example.android.nadris.network

import com.example.android.nadris.R
import com.example.android.nadris.database.PostData
import com.example.android.nadris.database.UserData
import com.example.android.nadris.domain.dataRvPost

object NetworkModelsMapper {
    fun postAsDatabaseModel(post:PostModel) =
        PostData(
            post.id ,
            post.subjectId ,
            post.content,
            post.votes ,
        )
    fun postAsDomainModel(post:PostModel) =
        dataRvPost(
            R.drawable.ic_google ,
            post.name ,
            post.subjectId,
            post.content ,
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

    fun authModelAsDomainModel(model: AuthModel)  = com.example.android.nadris.domain.UserData(
        Email = model.email,
        firstName = model.email,
        lastName = model.lastName,
        PhoneNumber = model.phoneNumber,
        Type = model.type,
        Gender = model.gender,
        Exp = model.exp,
        Grade = model.gradeId,
        University = model.university,
        College = model.college,
        Token = model.token,
        ExpiresOn = model.expiresOn
    )


}