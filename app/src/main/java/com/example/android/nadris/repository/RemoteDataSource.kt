package com.example.android.nadris.repository

import android.net.Uri
import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.network.firebase.services.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import java.io.File
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(
    private val authService: AuthService,
    private val userService: UserService,
    private val gradesService: GradesService,
    private val universitiesServices: UniversitiesServices,
    private val collegesService: CollegesService,
    private val storageService: StorageService,
    private val inquiriesService: InquiriesService,
    private val subjectsService: SubjectsService,
) {

    fun getCurrentUser() =
        authService.currentUser()

    fun singInWithEmailAndPassword(
        checkedEmail: String,
        checkedPassword: String,
    ): Task<DocumentSnapshot> {

        val signInTask = authService.singInWithEmailAndPassword(checkedEmail, checkedPassword)

        return signInTask.continueWithTask {

            var userID = it.result?.user?.uid

            userService.getUserData(userID!!)
        }
    }

    fun getGrades() = gradesService.getGrades()

    fun getUserData(userId:String) = userService.getUserData(userId)

    fun getSubjectsWithGrade(gradeDocRef: DocumentReference) =
        subjectsService.getSubjectsWithGrade(gradeDocRef)

    fun signUPWithEmailAndPassword(newUser: User, checkedPassword: String): Task<Void> {

        val signUpTask = authService.signUPWithEmailAndPassword(newUser.email, checkedPassword)

        return signUpTask.continueWithTask {

            var userID = it.result?.user?.uid

            newUser.id = userID!!

            userService.createNewUser(newUser)
        }
    }

    fun getAllUniversities() = universitiesServices.getAllUniversities()

    fun getCollegeForAUniversity(universityDocRef: DocumentReference) =
        collegesService.getCollegeForAUniversity(universityDocRef)

    fun addNewInquiryWithImage(inquiry: Inquiry, imageFile: File): Task<Void> {

        val inquiryID = inquiriesService.generateId()
        val imageFileName = (inquiryID + imageFile.extension)
        val imageUri = Uri.fromFile(imageFile)
        val uploadingImageTask = storageService.uploadInquiryImage(imageFileName,imageUri)

        return uploadingImageTask.continueWithTask {
            val imageLink = it.result?.storage
            inquiry.image_path = imageLink?.path.toString()
            inquiriesService.addNewInquiryWithID( inquiry,inquiryID)
        }
    }

    fun addNewInquiryWithoutImage(inquiry: Inquiry) =
        inquiriesService.addNewInquiry(inquiry)

    fun getAllInquiries() = Tasks.await(inquiriesService.getAllInquiries())


}