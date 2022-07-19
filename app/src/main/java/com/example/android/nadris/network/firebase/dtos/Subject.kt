package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Subject (
    val grade:DocumentReference? = null,
    val name_ar:String = "",
    val name_en:String = "",
    val numOfTeachersHaveCourseForSubject:Int = 0,
    var subject_id:String = ""
    ) {
}