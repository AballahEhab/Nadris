package com.example.android.nadris.network.dtos

data class UploadPhotoStatus (
    val succeeded:Boolean,
    val errors:List<String>
        )


data class UploadPhotoDTO (
    val imgStrB64:String,
        )