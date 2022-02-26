package com.example.android.nadris.network.models

data class NetworkPost(
    val id: Int,
    val subjectId: String,
    val content: String,
    val votes:Int,
    val publishComments:List<PublishCommentModel>,
    val time:String,
    val email:String,
    val name:String
)