package com.example.android.nadris.database.models

import androidx.room.Entity


@Entity
data class DatabaseSubject(
    val id:Int,
    val name_subject:String ,
    val count_teach:Int,
    val imv_subjects:Int
) 