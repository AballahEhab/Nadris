package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName


@IgnoreExtraProperties
data class User(
    @get:Exclude var id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val gender: Boolean = false,
    val grade: DocumentReference? = null,
    val following_users: List<String> = listOf(),
    val phoneNumber: String = "",
    @get: PropertyName("isATeacher") val isATeacher: Boolean = false,
    val university: DocumentReference? = null,
    val college: DocumentReference? = null,
    val image_path: String = "",
    @get:Exclude var image_File_Path: String? = "",
    val coursesSubscribedIds: List<String> = listOf(),
    val coursesSubscribedProgress: List<Int> = listOf(),
    val myCourses: List<String> = listOf(),
)