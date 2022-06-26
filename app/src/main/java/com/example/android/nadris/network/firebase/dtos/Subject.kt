package com.example.android.nadris.network.firebase.dtos

import com.google.firebase.firestore.DocumentReference

class Subject (
    val grade:DocumentReference,
    val name:String
        ) {
}