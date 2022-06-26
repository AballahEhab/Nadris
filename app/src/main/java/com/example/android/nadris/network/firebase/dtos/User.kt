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
    val followersIds: List<String> = listOf(),
    val phoneNumber: String = "",
    val type: Boolean = false,
    val university: DocumentReference? = null,
    val college: DocumentReference? = null,
    val imageLink: String = "",
){
    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', email='$email', gender=$gender, grade='$grade', followersIds=$followersIds, phoneNumber='$phoneNumber', type=$type, university='$university', college='$college', imageLink='$imageLink')"
    }
}