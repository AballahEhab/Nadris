package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class University (
    val name_ar:String = "",
    var docRef: DocumentReference? = null ){

}