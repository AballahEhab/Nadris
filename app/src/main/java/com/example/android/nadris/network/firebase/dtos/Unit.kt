package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Unit (
        var unitId: String = "",
        val name: String = "",
        var lessons: List<LessonDTO> = listOf(),
        )