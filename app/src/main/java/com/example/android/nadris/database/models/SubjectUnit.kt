package com.example.android.nadris.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubjectUnit (
    @PrimaryKey val id:Long,
    val name:String,
    val lessons:List<Lesson>,
    val icon:Int,
    var lessonsVisibility:Boolean = false // true = visible = unit expanded
    ){
    fun toggleExpandUnit(){
        lessonsVisibility = !lessonsVisibility
    }
}