package com.example.android.nadris.network

open class CreateAccountDataModel(
    val u_firstName: String,
    val u_lastName: String,
    val u_email: String,
    val u_password: String,
    val u_PhoneNumber: String,
    val u_Gender: Int,
)

data class CreateStudentAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val sectionId: Int = 1,
) : CreateAccountDataModel(firstName, lastName, email, password, phoneNumber, gender)

data class CreateTeacherAccountDataModelModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val gender: Int,
    val university: String,
    val college: String,
) : CreateAccountDataModel(firstName, lastName, email, password, phoneNumber, gender)

data class LoginAccountModel(
    val email: String,
    val password: String,
)

data class AuthModel(
    val message: String?,
    val isAuthenticated: Boolean,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val type: String?,
    val gender: Byte,
    val exp: Long,
    val gradeId: Int?,
    val university: String?,
    val college: String?,
    val roles: List<String>,
    val token: String,
    val expiresOn: String,
)

data class CreatePostModel(
    val SubjectId: Long,
    val Content: String,
    val ImgStrB64: String?,
)

data class NetworkPost(
    val id: Int,
    val subject: String,
    val content: String,
    val votes: Int,
    val comments: List<CommentModel>,
    val time: String,
    val email: String,
    val name: String,
    val imgStrB64: String,
    val pofilePicBase64: String,
)


data class VoteModel(
    val email: String,
    val value: Boolean,
    val postId: Int,
)

data class CommentModel(
    val email: String,
    val content: String,
    val postId: Int,
)