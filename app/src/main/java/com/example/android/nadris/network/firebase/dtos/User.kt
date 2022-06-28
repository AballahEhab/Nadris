package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class User(
    var id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val gender: Boolean = false,
    val grade: DocumentReference? = null,
    val following_users: List<String> = listOf(),
    val phoneNumber: String = "",
    val type: Boolean = false,
    val university: DocumentReference? = null,
    val college: DocumentReference? = null,
    val imageLink: String = "",
){

}