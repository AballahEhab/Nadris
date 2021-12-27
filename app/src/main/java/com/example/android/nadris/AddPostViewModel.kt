package com.example.android.nadris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
var questionList= mutableListOf<AddPostViewModel>()
class AddPostViewModel : ViewModel() {
    var subjects : MutableLiveData<String> = MutableLiveData<String>()
    var question : MutableLiveData<String> = MutableLiveData<String>()
    val id : Int?= questionList.size
}