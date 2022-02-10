package com.example.android.nadris.ui.addPosts

import android.content.Intent
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddPostViewModel @Inject constructor() : ViewModel() {
    var subjects : MutableLiveData<String> = MutableLiveData<String>()
    var question : MutableLiveData<String> = MutableLiveData<String>()
    var IMAGE_REQUEST_CODE=456
    var READ_STORAGE_PERM = 123
    var image : Int = 0











}