package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

@IgnoreExtraProperties
class Inquiry(
    val body:String = "",
    var image_path:String = "",
    var voted_user_ids:List<String> = listOf(),
    val subject : DocumentReference? = null,
    @ServerTimestamp
    var time:Date? = null,
    val userID:String = "")