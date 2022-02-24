package com.example.android.nadris.database.models

import androidx.room.Entity

@Entity
data class Lesson (
    val lessonNum:String,
    val lessonName:String
    )