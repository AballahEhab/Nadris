package com.example.android.nadris.network

import com.example.android.nadris.database.PostData
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
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val University: String?,
    val College: String?,
):CreateAccountDataModel(firstName,lastName,email,password,phoneNumber,gender)





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
    val type: String?,
    val gender: Byte,
    val exp: Long,
    val gradeId: Int?,
    val university: String?,
    val college: String?,
    val roles:List<String>,
    val token: String,
    val expiresOn: String,
)
data class CreatePostModel(
    val subject: String,
    val body: String,
)

data class PostModel(
    val id: Int,
    val subjectId: String,
    val content: String,
    val votes:Int,
    val comments:List<CommentModel>,
    val time:String,
    val email:String,
    val name:String


)
data class VoteModel(
    val email: String,
    val value: Boolean,
    val postId:Int
)

data class CommentModel(
    val email: String,
    val content: String,
    val postId:Int
)








fun postAsDatabaseModel(post:PostModel) =
    PostData(
        post.id ,
        post.subjectId ,
        post.content,
        post.votes ,
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

