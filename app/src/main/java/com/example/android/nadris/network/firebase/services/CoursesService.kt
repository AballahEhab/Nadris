package com.example.android.nadris.network.firebase.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import javax.inject.Inject

class CoursesService @Inject constructor(val db: FirebaseFirestore) {

    private val coursesCollection = db.collection("courses")

    fun getCoursesWithIds(coursesIds: List<String>) =
        coursesCollection
            .whereIn(FieldPath.documentId(), coursesIds)
            .get()

    fun getCoursesWithId(coursesId: String) =
        coursesCollection
            .document(coursesId)
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

    fun registerStudentToACourse(
        studentID: String,
        courseID: String,
    ): Task<Void> {
        val updatedFields = mapOf("subscribedStudentsIds" to FieldValue.arrayUnion(studentID),"subscribedStudentsRatings" to FieldValue.arrayUnion(0))

       return coursesCollection.document(courseID).update(updatedFields)

    }

}
