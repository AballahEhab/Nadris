package com.example.android.nadris.network

import com.example.android.nadris.database.UserData




open class CreateAccountDataModel(
    val u_firstName: String,
    val u_lastName: String,
    val u_email: String,
    val u_password: String,
    val u_PhoneNumber: String,
    val u_Gender: Int){

}


class CreateStudentAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val sectionId: Int = 1
) : CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)


class CreateTeacherAccountDataModelModel(
    val T_firstName: String,
    val T_lastName: String,
    val T_email: String,
    val T_password: String,
    val T_PhoneNumber: String,
    val T_Gender: Int,
    val University: String?,
    val College: String?,
):CreateAccountDataModel(T_firstName,T_lastName,T_email,T_password,T_PhoneNumber,T_Gender)





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
    val gradeId: Int?,
    val university: String?,
    val college: String?,
    val roles:List<String>,
    val token: String,
    val expiresOn: String,
)

fun asDataBaseModel(model: AuthModel) :UserData = UserData(
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
