package com.example.android.nadris.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostData(

    @PrimaryKey val postId: Int,
    val subject: String,
    val body: String,
    val votes:Int
)

