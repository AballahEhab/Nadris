package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class SubjectsService @Inject constructor(val db: FirebaseFirestore) {

    private val inquiriesCollection = db.collection("subjects")

    fun getSubjectsWithGrade(gradeDocRef: DocumentReference): Task<QuerySnapshot> {
        val subjectsQuery = inquiriesCollection.whereEqualTo("grade", gradeDocRef)
        return subjectsQuery.get()
    }
}