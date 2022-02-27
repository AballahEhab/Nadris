package com.example.android.nadris.network.dtos

open  class PublishCommentModel(
    val email: String,
    val content: String,
    val postId:Int,
)

 class CommentModel(
     emailP: String,
     contentP: String,
     postIdP:Int,
    ) : PublishCommentModel(emailP,contentP,postIdP){

     lateinit var name:String

        constructor(
            emailP: String,
            nameP:String,
            contentP: String,
            postIdP:Int) : this(emailP,contentP,postIdP){
                this.name = nameP
            }
 }
