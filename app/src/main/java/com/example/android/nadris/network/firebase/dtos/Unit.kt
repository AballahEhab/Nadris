package com.example.android.nadris.network.firebase.dtos

import com.example.android.nadris.data.models.LessonDTO

data class Unit (
        var unitId: String,
        val name: String,
        val lessons: List<LessonDTO>,
        )