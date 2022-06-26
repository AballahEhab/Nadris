package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class CollegesService @Inject constructor(val db: FirebaseFirestore) {

    private val universitiesCollection = db.collection("colleges")

    fun getCollegeForAUniversity( universityDocRef: DocumentReference): Task<QuerySnapshot> {
        val query = universitiesCollection.whereArrayContains("universities_available_in",universityDocRef)
        return query.get()
    }
}