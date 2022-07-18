package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

@IgnoreExtraProperties
class Inquiry(
    val body: String? = null,
    var image_path: String? = null,
    var replies_num:Int = 0,
    @get:Exclude var image_File_Path: String? = "",
    var voted_user_ids: List<String> = listOf(),
    val subject_id: String? = null,
    @get:Exclude var userProfileImagePah: String? = null,
    @get:Exclude var subjectName: String? = null,
    @ServerTimestamp var time: Date? = null,
    val userID: String? = null,
    @get:Exclude var id: String? = null){


}