package com.example.android.nadris.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.nadris.network.CommentModel

@Entity
data class DatabasePost(

    @PrimaryKey val postId: Int,
    var imageStudent : Int,
    val subjectId: String,
    val content: String,
    val votes:Int,
    val time:String,
    val email:String,
    val name:String
)
