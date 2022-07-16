package com.example.android.nadris.network.firebase.services

import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import javax.inject.Inject

class InquiriesService @Inject constructor(val db: FirebaseFirestore) {

    private val inquiriesCollection = db.collection("inquiries")

    fun getAllInquiries(): Task<QuerySnapshot>  =
        inquiriesCollection.get()

    fun addNewInquiry(newInquiry:Inquiry): Task<DocumentReference>  =
         inquiriesCollection.add(newInquiry)

    fun generateId() = inquiriesCollection.document().id

    fun addNewInquiryWithID(inquiry: Inquiry, inquiryID: String): Task<Void> {
        val inquiryDoc = inquiriesCollection.document(inquiryID)
        return inquiryDoc.set(inquiry)
    }

    fun getCommentsForAnInquiry(id: String): Task<QuerySnapshot> {
        val repliesCollection = getRepliesCollectionRef(id)
        return repliesCollection.get()
    }

    private fun getRepliesCollectionRef(inquiryId:String) : CollectionReference =
        inquiriesCollection.document(inquiryId).collection("replies")

    fun getInquiryWithId(inquiryId: String): Task<DocumentSnapshot> {
        val dcSnapshot = inquiriesCollection.document(inquiryId)
        return dcSnapshot.get()
    }

    fun setVotedUserIdsForInquiry(inquiryId: String, votedIdsList: MutableList<String>?): Task<Void> {
        val dcSnapshot = inquiriesCollection.document(inquiryId)
        val updatedUserIds = mapOf("voted_user_ids" to votedIdsList)
        return dcSnapshot.update(updatedUserIds)
    }


}

