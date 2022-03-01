package com.example.android.nadris.network.dtos

data  class PublishCommentModel(
    val content: String,
    val postId:Long,
)
data class CommentModel(
    val id: Long,
    val content: String,
    val name: String,
    val time:String,
)



// class CommentModel(
//     emailP: String,
//     contentP: String,
//     postIdP:Int,
//    ) : PublishCommentModel(emailP,contentP,postIdP){
//
//     lateinit var name:String
//
//        constructor(
//            emailP: String,
//            nameP:String,
//            contentP: String,
//            postIdP:Int) : this(emailP,contentP,postIdP){
//                this.name = nameP
//            }
// }
