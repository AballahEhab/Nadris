package com.example.android.nadris.network.firebase.services

import com.example.android.nadris.network.firebase.dtos.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserService @Inject constructor(val db: FirebaseFirestore) {

    private val usersCollection = db.collection("users")

    fun createNewUser(newUser:User): Task<Void> {
        val userDc = usersCollection.document(newUser.id)
        return userDc.set(newUser)
    }

    fun getUserData(userId:String): Task<DocumentSnapshot> {
        val userDc = usersCollection.document(userId)
        return userDc.get()
    }

    fun addCourseIdToStudentData(selectedCourseID: String, userID: String): Task<Void> {
        val updatedFields = mapOf("coursesSubscribedIds" to FieldValue.arrayUnion(selectedCourseID),"coursesSubscribedProgress" to FieldValue.arrayUnion(0))
        return usersCollection.document(userID).update(updatedFields)
    }

    fun addCourseIdToTeacherData(coursesId: String, userId: String): Task<Void> {
        val updatedFields = mapOf("myCourses" to FieldValue.arrayUnion(coursesId))
        return usersCollection.document(userId).update(updatedFields)
    }

}