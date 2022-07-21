package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class LessonDTO(
    var lessonId: String = "",
    var name: String = "",
)