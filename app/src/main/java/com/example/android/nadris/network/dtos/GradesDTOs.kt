package com.example.android.nadris.network.dtos

data class GradeDTO(
    val id: Long,
    val name: String,
)

data class GradeWithSectionDTO(
    val id: Long,
    val name: String,
    val grade: String,
)