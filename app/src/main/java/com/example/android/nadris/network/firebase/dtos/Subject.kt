package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Subject (
    val grade:DocumentReference? = null ,
    val name_ar:String = "",
    val name_en:String = "",
    var subject_id:String = ""
    ) {
}