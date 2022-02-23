package com.example.android.nadris.domain

data class SubjectUnit (
    val name:String,
//    val lessons:List<Lesson>,
    val icon:Int,
    var lessonsVisibility:Boolean = false
    ){
    fun toggleExpandUnit(){
        lessonsVisibility = !lessonsVisibility
    }
}