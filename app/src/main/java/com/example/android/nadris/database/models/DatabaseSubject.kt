package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DatabaseSubject(
    @PrimaryKey val id:Int,
    val name_subject:String,
    val count_teach:Int,
    val imv_subjects:Int
) 