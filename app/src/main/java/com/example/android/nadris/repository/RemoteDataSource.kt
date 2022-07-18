package com.example.android.nadris.repository

import android.net.Uri
import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.example.android.nadris.network.firebase.dtos.Reply
import com.example.android.nadris.network.firebase.dtos.Subject
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.network.firebase.services.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FileDownloadTask
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

    val subjectsList: MutableList<Subject> = mutableListOf()

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

    fun getUserData(userId: String) = userService.getUserData(userId)

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
        val imageFileName = (inquiryID + "." + imageFile.extension)
        val imageUri = Uri.fromFile(imageFile)
        val uploadingImageTask = storageService.uploadInquiryImage(imageFileName, imageUri)

        return uploadingImageTask.continueWithTask {
            inquiry.image_path = it.result?.storage?.path.toString()
            inquiriesService.addNewInquiryWithID(inquiry, inquiryID)
        }
    }

    fun addNewReply(reply: Reply,inquiryId: String): Task<Void> =
        inquiriesService.addNewReplyWithID(reply,inquiryId)


    fun addNewInquiryWithoutImage(inquiry: Inquiry): Task<DocumentReference> =
        inquiriesService.addNewInquiry(inquiry)

    fun getAllInquiries() :Task<QuerySnapshot> = inquiriesService.getAllInquiries()

    fun getInquiryImage(file: File, imagePath: String,): FileDownloadTask =
         storageService.getInquiryImage(file, imagePath)

    fun getProfileImage(file: File, imagePath: String): FileDownloadTask =
         storageService.getProfileImage(file, imagePath)


    fun getCommentsForAnInquiry(id: String) :Task<QuerySnapshot> =
        inquiriesService.getCommentsForAnInquiry(id)


    fun getSubjectWithId(subjectId: String): Subject {
        var subject = subjectsList.find { it.subject_id == subjectId }

        try {
            if (subject == null) {
                val dcSnap = Tasks.await(subjectsService.getSubjectsWithSubjectId(subjectId))
                subject = dcSnap.toObject<Subject>()
                subject?.subject_id = dcSnap.id
                subjectsList.add(subject!!)
            }
            return subject
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun getInquiryWithId(inquiryId: String) =
        inquiriesService.getInquiryWithId(inquiryId)

    fun setVotedUserIdsForInquiry(inquiryId: String, votedIdsList: MutableList<String>?)  =
        inquiriesService.setVotedUserIdsForInquiry(inquiryId, votedIdsList)


    fun incrementReplies(inquiryId: String)  =
        inquiriesService.incrementReplies(inquiryId)



    fun signOut() {
        authService.signOut()
    }

}