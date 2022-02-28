package com.example.android.nadris.services

import android.graphics.Bitmap
import java.io.File

interface IConverter {
    fun convertToBase64(attachment: File): String
    fun convertFromBase64(b64String: String, path: String): File
    fun convertFromBase64ToBitmap(strB64: String,fileName: String): Bitmap
    fun convertBitmapToFile(bitmap: Bitmap): File
    fun convertBitmapToBase64(bitmap: Bitmap): String
}