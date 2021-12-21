package com.example.android.nadris.network

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class CreateAccountData (
    val firstName:String,
    val lastName:String,
    val userName :String ,
    val email:String,
    val password:String,
    val phoneNumber:String,
    val gender:String,
    val type:String,
    val grade:Int,
    val university:String? = null,
    val colleage: String?

    )

class GetAccountData (
    @SerializedName("message")
    val errorMessage :String?,
    val isAuthenticated:Boolean,
    val userName:String,
    val email:String,
    val phoneNumber :String,
    val type:String,
    val gender:String,
    val exp:Long,
    val grade:Int,
    val university:String?,
    val colleage: String?,
    val roles:List<String>,
    val token:String,
    val expiresOn:String // this may be date type
)


data class LoginAccountModel(
    val email: String,
    val password: String
)
data class Token(
    val token:String
)