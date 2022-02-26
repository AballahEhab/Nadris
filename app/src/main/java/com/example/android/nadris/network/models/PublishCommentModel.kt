package com.example.android.nadris.network.models

open  class PublishCommentModel(
    val email: String,
    val content: String,
    val postId:Int
)

data class GetCommentModel(
    val emailP: String,
    val contentP: String,
    val postIdP:Int,
val name:String) : PublishCommentModel(emailP,contentP,postIdP)
