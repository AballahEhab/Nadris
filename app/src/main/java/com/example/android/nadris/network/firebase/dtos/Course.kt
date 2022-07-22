package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
class Course(
    @get:Exclude var courseId: String = "",
    val gradeRef: DocumentReference? = null,
    val ownerTeacherID: String = "",
    val subjectId: String = "",
    val subscribedStudentsIds: List<String> = listOf(),
    val subscribedStudentsRatings: List<Int> = listOf(),
    @ServerTimestamp val timeCreated: java.util.Date? = null,
//    val coursesSubscribedProgress:List<Int> = listOf(),
//    @get: Exclude var isJoined:Boolean = false,
    @get:Exclude var subjectName: String = "",
    @get:Exclude var teacherName: String = "",
    @get:Exclude var gradeName: String = "",
    @get: Exclude var teacherImagePath: String = ""
)