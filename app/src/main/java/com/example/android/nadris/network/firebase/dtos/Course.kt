package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.type.Date

class Course (
    val grade:DocumentReference,
    val ownerTeacherID:String = "",
    val subject:String = "",
    val subjectReference:DocumentReference ,
    val timeCreated:Date)