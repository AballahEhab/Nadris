package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.CollectionReference
import com.google.type.DateTime

class Inquiries(
    val replies:CollectionReference,
    val body:String = "",
    val image:String = "",
    val replies_ids :List<String> = listOf(),
    val subject :String = "",
    val time:DateTime ,
    val userId:String = "",
    val voted_user_ids :List<String> = listOf()
)