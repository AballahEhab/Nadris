package com.example.android.nadris.network.dtos

data class NetworkPost(
    val id: Long,
    val subject: String,
    val content: String,
    val votes:Int,
    val numOfComments:Int,
    val time:String,
    val email:String,
    val name:String,
    val isVoted:Boolean
)