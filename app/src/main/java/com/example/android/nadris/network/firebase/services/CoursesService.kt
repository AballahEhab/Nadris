package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class CoursesService @Inject constructor(val db: FirebaseFirestore) {

    private val coursesCollection = db.collection("courses")

    fun getCoursesWithIds(coursesIds: List<String>) =
        coursesCollection
            .whereIn(FieldPath.documentId(), coursesIds)
            .get()


    fun getCoursesWithSubjectIds(gradeId: String) =
        coursesCollection
            .whereEqualTo("subjectId", gradeId)
            .get()

    fun getCourseUnits(gradeId: String) : Task<QuerySnapshot> =
        coursesCollection
            .document(gradeId)
            .collection("units")
            .get()

    fun getLessonsCollection(unitDocRef: DocumentReference) =
        unitDocRef.collection("lessons")
            .get()

}
