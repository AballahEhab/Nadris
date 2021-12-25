package com.example.android.nadris.ui.phoneVerification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhoneViewModelFactory(private val receivedOTB:String):ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("unchecked_cast")
        if(modelClass.isAssignableFrom(PhoneViewModel::class.java)){
            return PhoneViewModel(receivedOTB) as T
        }
                throw IllegalArgumentException("Unknown ViewModel class")
    }


}