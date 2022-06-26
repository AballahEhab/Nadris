package com.example.android.nadris.repository

import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.network.firebase.services.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(
    private val authService: AuthService,
    private val userService: UserService,
    private val gradesService: GradesService,
    private val universitiesServices: UniversitiesServices,
    private val collegesService: CollegesService
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

    fun getGrades() =  gradesService.getGrades()

    fun signUPWithEmailAndPassword(newUser: User,checkedPassword:String): Task<Void> {

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


}