package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.io.File
import java.util.*

@IgnoreExtraProperties
class Inquiry(
    val body: String? = null,
    var image_path: String? = null,
    @get:Exclude val image_File: File? = null,
    var voted_user_ids: List<String> = listOf(),
    val subject: DocumentReference? = null,
    @get:Exclude val subjectName: String? = null,
    @ServerTimestamp var time: Date? = null,
    val userID: String? = null,
    @get:Exclude val id: String? = null)