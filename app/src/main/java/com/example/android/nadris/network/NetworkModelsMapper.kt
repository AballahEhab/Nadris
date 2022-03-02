package com.example.android.nadris.network

import android.util.Log
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.database.models.DatabasePost
import com.example.android.nadris.database.models.UserData
import com.example.android.nadris.network.dtos.AuthModel
import com.example.android.nadris.network.dtos.NetworkPost
import com.example.android.nadris.services.Converter

object NetworkModelsMapper {
    fun postAsDatabaseModel(networkPost: NetworkPost): DatabasePost {
        var hasImage = false
        Log.i("post", networkPost.toString())
        if (!networkPost.imgStrB64.isNullOrEmpty()) {
            NadrisApplication.instance?.let {
              var res=  Converter(it.applicationContext).convertFromBase64ToBitmap(networkPost.imgStrB64,
                    networkPost.id.toString())
            Log.i("img",res.byteCount.toString())
            }

            hasImage = true


        }
        return DatabasePost(
            networkPost.id,
            hasImage,
            networkPost.subject,
            networkPost.content,
            networkPost.votes,
            networkPost.numOfComments,
            networkPost.time,
            networkPost.email,
            networkPost.name,
            networkPost.isVoted,
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