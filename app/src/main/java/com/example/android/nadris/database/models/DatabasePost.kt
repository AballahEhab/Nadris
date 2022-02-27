package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePost(

    @PrimaryKey val postId: Int,
    var imageStudent : Int,
    val subjectId: String,
    val content: String,
    val votesNum:Int,
    val commentsNum:Int,
    val time:String,
    val email:String,
    val name:String
)
