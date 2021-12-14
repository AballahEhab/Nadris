package com.example.android.nadris.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserData(
    @ColumnInfo val temp_token: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String,
    @PrimaryKey  val email:String = String()
)