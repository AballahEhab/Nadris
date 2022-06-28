package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.Date

@IgnoreExtraProperties
class Inquiry(
    val body:String = "",
    var image:String = "",
    val subject : DocumentReference? = null,
    @ServerTimestamp
    val time:Date? = null,
    val userID:String = "")