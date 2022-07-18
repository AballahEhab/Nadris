package com.example.android.nadris.network.firebase.services

import android.net.Uri
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.File
import javax.inject.Inject


class StorageService @Inject constructor(private val storage  : FirebaseStorage) {
    private val storageRef = storage.reference

    private val inquiresImagesRef = storageRef.child("inquiries_images")
    private val profileImagesRef = storageRef.child("profile_images")
    private val lessonsFilesRef = storageRef.child("lessons_files")

    fun uploadInquiryImage(fileNameOnStorage:String, imageUri:Uri): UploadTask {
        val imageRef = inquiresImagesRef.child(fileNameOnStorage)
        return imageRef.putFile(imageUri)
    }

    fun getInquiryImage(file:File,imagePath:String): FileDownloadTask {
        val imageRef = storageRef.child(imagePath)
        return imageRef.getFile(file)
    }

    fun uploadProfileImage(fileNameOnStorage:String, imageUri:Uri): UploadTask {
        val imageRef = profileImagesRef.child(fileNameOnStorage)
        return imageRef.putFile(imageUri)
    }

    fun getProfileImage(file:File,imagePath:String): FileDownloadTask {
        val imageRef = storageRef.child(imagePath)
        return imageRef.getFile(file)
    }

}