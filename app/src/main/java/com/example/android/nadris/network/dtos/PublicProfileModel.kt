package com.example.android.nadris.network.dtos

data class PublicProfileModel(
    val imgProfile:String,
    val name:String,
    val type:String,
    val numOfPosts:Int,
    val numOfFollowers:Int,
    val numOfFollowing:Int,
    val postsProfileList:List<NetworkPost>)