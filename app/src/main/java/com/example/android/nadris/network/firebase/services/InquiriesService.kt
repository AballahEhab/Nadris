package com.example.android.nadris.network.firebase.services

import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class InquiriesService @Inject constructor(val db: FirebaseFirestore) {

    private val inquiriesCollection = db.collection("inquiries")

    fun getAllInquiries(): Task<QuerySnapshot>  =
        inquiriesCollection.get()

    fun addNewInquiry(newInquiry:Inquiry): Task<DocumentReference> {
        return inquiriesCollection.add(newInquiry)
    }

    fun createNewBlankInquiry() =
        inquiriesCollection.document().id

    fun addNewInquiryWithID(inquiry: Inquiry, inquiryID: String): Task<Void> {
        val inquiryDoc = inquiriesCollection.document(inquiryID)
        return inquiryDoc.set(inquiry)
    }
}
