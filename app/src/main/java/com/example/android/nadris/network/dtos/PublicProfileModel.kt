package com.example.android.nadris.network.dtos

data class PublicProfileModel(
    val university:String?,
    val college:String?,
    val exp:Int?,
    val grade:String?,
    val firstName:String,
    val lastName:String,
    val email:String,
    val phoneNumber:String,
    val type:String,
    val gender:String,
    val profilePicStrB64:String,
    val numOfFollowers:Int,
    val numOfFollowing:Int,
    )