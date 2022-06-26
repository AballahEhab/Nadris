package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GradesService @Inject constructor(val db: FirebaseFirestore) {

    private val gradesCollection = db.collection("grades")

    fun getGrades(): Task<QuerySnapshot> {
        val query = gradesCollection
        return query.get()
    }

}