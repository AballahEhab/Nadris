package com.example.android.nadris.network.firebase.dtos

data class CourseSection (
    val sectionId:String,
    val youtubeVideoUrl:String,
    val audioFilePathOnStorage:String,
    val pdfFilePathOnStorage:String,
    val formattedText:String,

        )