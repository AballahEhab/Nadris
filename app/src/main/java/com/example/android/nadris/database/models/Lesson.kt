package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lesson (
    @PrimaryKey val lessonNum:String,
    val lessonName:String
    )