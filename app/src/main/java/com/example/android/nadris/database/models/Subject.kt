package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubjectUnit (
    @PrimaryKey val unitId:Long,
    val name:String,
    val icon:Int,
    var lessonsVisibility:Boolean = false // true = visible = unit expanded
    ){
    fun toggleExpandUnit(){
        lessonsVisibility = !lessonsVisibility
    }
}


@Entity
data class TeacherSubject(
    @PrimaryKey val id: Long,
    val  name: String,
    val section :String,
    val term : String,
    val grade: String,
    val teacherName : String
)