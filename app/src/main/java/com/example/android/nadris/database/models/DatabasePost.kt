package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePost(

    @PrimaryKey val postId: Int,
    var imageStudent : Int,
    val subject: String,
    val content: String,
    val votes:Int,
    val time:String,
    val email:String,
    val name:String
)
