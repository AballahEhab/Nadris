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
    val message: String?,
    val isAuthenticated: Boolean,
    val firstName:String,
    val lastName:String,
    val email: String,
    val phoneNumber: String,
    val type: String,
    val gender: Byte,
    val exp: Long,
    val grade: Int,
    val university: String?,
    val college: String?,
    val roles:List<String>,
    val token: String,
    val expiresOn: String,
)

//fun AuthModel.asDomainModel():UserData{}
fun AuthModel.asDataBaseModel() :UserData = UserData(
      Email = this.email,
      firstName = this.email,
      lastName = this.lastName,
      PhoneNumber = this.phoneNumber,
      Type = this.type,
      Gender = this.gender,
      Exp = this.exp,
      Grade = this.grade,
      University = this.university,
      College = this.college,
      Token = this.token
    )
