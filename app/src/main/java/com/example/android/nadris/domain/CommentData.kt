package com.example.android.nadris.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


data class CommentData(

    val email: String,
    val name:String,
    val content: String,
    val postId:Int
)