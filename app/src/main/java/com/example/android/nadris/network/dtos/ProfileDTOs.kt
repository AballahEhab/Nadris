package com.example.android.nadris.network.dtos


data class ProfileInfoDTO (val firstName:String,
                           val lastName:String,
                           val gender:String,
                           val email:String,
                           val phoneNumber:String,
                           val type:String,
                           val numOfPosts:Long,
                           val numOfFollowers:Long,
                           val numOfFollowing:Long,
                           val college:String?,
                           val university:String?,
                           val exp:Long?,
                           val grade:String?,
                           val profilePicStrB64:String?,
                           var isFollowed:Boolean
                           )
//exp ==>  experince of Student

{

}


//data transfer object
//used when model return from api dont same module Storage in data base