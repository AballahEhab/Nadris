package com.example.android.nadris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.matchPasswords
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ChangePasswordViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var oldPassowrd: MutableLiveData<String> = MutableLiveData()
    var newPassowrd: MutableLiveData<String> = MutableLiveData()
    var reNewPassowrd: MutableLiveData<String> = MutableLiveData()

    fun validateNewPassword(){
        if(newPassowrd.value.toString() == reNewPassowrd.value.toString()){

        }
    }

//    if(binding.edtNewPassowrd.text.toString() == binding.edtRetypeNewPassowrd.text.toString()&&
//    binding.edtNewPassowrd.text.toString().isNotEmpty() &&
//    binding.edtOldPassword.text.toString().isNotEmpty()){
//        //send old password to api to cheeck on true or false
//
//    }else{
//        Toast.makeText(requireContext(),"الباسورد غير متساوي", Toast.LENGTH_SHORT).show()
//    }

}