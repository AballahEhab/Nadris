package com.example.android.nadris.network

import com.example.android.nadris.database.UserData
import com.google.gson.annotations.SerializedName
import java.time.format.DateTimeFormatter

class CreateAccountData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val PhoneNumber: String,
    val Gender: Byte,
    val Type: String,
    val Grade: Int,
    val University: String?,
    val College: String?,
)
data class LoginAccountModel(
    val email: String,
    val password: String,
)

data class AuthModel(
    val Message: String?,
    val isAuthenticated: Boolean,
    val Email: String,
    val FirstName:String,
    val LastName:String,
    val PhoneNumber: String,
    val Type: String,
    val Gender: Byte,
    val Exp: Long,
    val Grade: Int,
    val University: String?,
    val College: String?,
    val Token: String,
    val ExpiresOn: DateTimeFormatter,
)

//fun AuthModel.asDomainModel():UserData{}
//fun AuthModel.asNetworkModel(){}