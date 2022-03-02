package com.example.android.nadris.network.dtos

data class NetworkPost(
    val id: Long,
    val subject: String,
    val content: String,
    val votes: Long,
    val numOfComments: Long,
    val time: String,
    val email: String,
    val name: String,
    val imgStrB64: String?,
    val isVoted: Boolean,
    val ProfilePicBase64: String,
)
