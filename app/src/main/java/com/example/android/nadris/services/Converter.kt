package com.example.android.nadris.services


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class Converter @Inject constructor(@ApplicationContext private val context: Context) : IConverter {

    override fun convertToBase64(attachment: File): String {
        val bytes = attachment.readBytes()
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    override fun convertFromBase64(b64String: String, path: String): File {
        val bytes = Base64.decode(b64String, Base64.NO_WRAP)
        val file = File(path)
        file.writeBytes(bytes)
        return file
    }

    override fun convertFromBase64ToBitmap(strB64: String, fileName: String): Bitmap {
        val f = File(context.cacheDir, fileName)
        f.createNewFile()
        val file = convertFromBase64(strB64, f.path)
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    override fun convertBitmapToFile(bitmap: Bitmap): File {
        //create a file to write bitmap data
        val f = File(context.cacheDir, "temp")
        f.createNewFile()
        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos)
        val bitmapData = bos.toByteArray()
        //write the bytes in file
        val fos = FileOutputStream(f)
        fos.write(bitmapData)
        fos.flush()
        fos.close()
        return f
    }

    override fun convertBitmapToBase64(bitmap: Bitmap): String {
        return convertToBase64(convertBitmapToFile(bitmap))
    }
}
