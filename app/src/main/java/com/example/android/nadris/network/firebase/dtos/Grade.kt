package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Grade (
    var id:String?=null,
    val grade:String = "",
    val level:String = "",
    val department:String = "",
    val division:String = "",
    val name_ar:String = "",
    var gradeReference:DocumentReference? = null
        ){
    override fun toString(): String {
        return "Grade(grade='$grade', level='$level', department='$department', division='$division', name_ar='$name_ar', gradeReference=$gradeReference)"
    }
}