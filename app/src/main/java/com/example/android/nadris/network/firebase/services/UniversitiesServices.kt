package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class UniversitiesServices @Inject constructor(val db: FirebaseFirestore) {

    private val universitiesCollection = db.collection("universities")

    fun getAllUniversities(): Task<QuerySnapshot> =
        universitiesCollection.get()


}