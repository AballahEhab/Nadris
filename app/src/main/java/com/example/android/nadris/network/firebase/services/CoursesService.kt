package com.example.android.nadris.network.firebase.services

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CoursesService @Inject constructor(val db: FirebaseFirestore) {

    private val coursesCollection = db.collection("courses")




}