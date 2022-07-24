package com.example.android.nadris.network.firebase.services

import com.example.android.nadris.network.firebase.dtos.Inquiry
import com.example.android.nadris.network.firebase.dtos.Reply
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
//    fun updateInquiryWithID(inquiry: Inquiry): Task<Void> {
//        val inquiryDoc = inquiriesCollection.document(inquiry.id!!)
//        //subject id, grade id, body, image path,
//        return inquiryDoc.update(inquiry)
//    }

    fun getCommentsForAnInquiry(id: String): Task<QuerySnapshot> {
        val repliesCollection = getRepliesCollectionRef(id)
        return repliesCollection.get()
    }

    fun getInquiryWithId(inquiryId: String): Task<DocumentSnapshot> {
        val dcSnapshot = inquiriesCollection.document(inquiryId)
        return dcSnapshot.get()
    }

    fun setVotedUserIdsForInquiry(inquiryId: String, votedIdsList: MutableList<String>?): Task<Void> {
        val dcSnapshot = inquiriesCollection.document(inquiryId)
        val updatedUserIds = mapOf("voted_user_ids" to votedIdsList)
        return dcSnapshot.update(updatedUserIds)
    }

    fun addNewReplyWithID(reply: Reply, inquiryId: String): Task<Void> {
        val repliesCollectionRef  = getRepliesCollectionRef(inquiryId)
        val inquiryDoc = repliesCollectionRef.document()
        return inquiryDoc.set(reply)
    }

    fun incrementReplies(inquiryId: String): Task<Void> {
        val inquiryDoc = inquiriesCollection.document(inquiryId)
        return inquiryDoc.update("replies_num",FieldValue.increment(1))
    }


    //private functions
    private fun getRepliesCollectionRef(inquiryId:String) : CollectionReference =
        inquiriesCollection.document(inquiryId).collection("replies")


}

