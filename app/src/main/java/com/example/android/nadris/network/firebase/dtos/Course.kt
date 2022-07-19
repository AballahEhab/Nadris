package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
class Course (
    @set:DocumentId @get:Exclude var id: String = "",
    val gradeID:String= "",
    val ownerTeacherID:String = "",
    val subjectId:String = "",
    @get:Exclude var subjectName:String = "",
    @get:Exclude var teacherName:String = "",
    val subscribedStudentsIds:List<String> = listOf(),
    val coursesSubscribedProgress:List<Int> = listOf(),
    @ServerTimestamp val timeCreated:java.util.Date? = null
    )