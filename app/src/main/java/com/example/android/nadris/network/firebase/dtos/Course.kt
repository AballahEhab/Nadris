package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.*

@IgnoreExtraProperties
class Course (
    @set:DocumentId @get:Exclude var courseId: String = "",
    val gradeID:DocumentReference? = null,
    val ownerTeacherID:String = "",
    val subjectId:String = "",
    @get:Exclude var subjectName:String = "",
    @get:Exclude var teacherName:String = "",
    val subscribedStudentsIds:List<String> = listOf(),
//    val coursesSubscribedProgress:List<Int> = listOf(),
    @ServerTimestamp val timeCreated:java.util.Date? = null
    )