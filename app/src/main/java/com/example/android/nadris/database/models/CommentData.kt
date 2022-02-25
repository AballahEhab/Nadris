package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentData(

    @PrimaryKey val email: String,
    val name:String,
    val content: String,
    val postId:Int
)//ToDo add variable name as name acount will add coment