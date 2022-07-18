package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

@IgnoreExtraProperties
data class Reply (
    @get: Exclude var replyId:String? = null,
    @ServerTimestamp var time: Date? = null,
    val replyBody:String? = null,
    val userId :String? = null,
    @get: Exclude var userFullName:String? = null,
    val votedUsersIds:List<String> = listOf(),
)