package com.example.android.nadris.network.firebase.services

import com.example.android.nadris.network.firebase.dtos.QuizData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class QuizService @Inject constructor(val db: FirebaseFirestore) {

    private val quizzesCollection = db.collection("quizzes")

    fun getQuizzesWithIds(quizIds: List<String>) =
        quizzesCollection
            .whereIn(FieldPath.documentId(), quizIds)
            .get()

    fun getQuizWithId(quizId: String) =
        quizzesCollection
            .document(quizId)
            .get()


    fun getQuizzesWithSubjectId(subjectId: String) =
        quizzesCollection
            .whereEqualTo("subjectId", subjectId)
            .get()

    fun getQuizWithTeacherId(teacherId: String) =
        quizzesCollection
            .whereEqualTo("ownerTeacherId", teacherId)
            .get()


    fun generateQuizId(): String  =
        quizzesCollection.document().id

    fun addNewQuiz(question: QuizData) =
        quizzesCollection.document().set(question)


    fun getQuestionsForAQuiz(quizDocRef: DocumentReference) =
        quizDocRef.get()

    fun getQuizzesWithGradeRef(grade: DocumentReference) =
        quizzesCollection
            .whereEqualTo("gradeRef",grade)
            .get()

}