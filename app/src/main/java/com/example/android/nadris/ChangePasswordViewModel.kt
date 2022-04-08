package com.example.android.nadris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ChangePasswordViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var oldPassowrd: MutableLiveData<String> = MutableLiveData()
    var newPassowrd: MutableLiveData<String> = MutableLiveData()

    fun updatePassword(){

    }

}