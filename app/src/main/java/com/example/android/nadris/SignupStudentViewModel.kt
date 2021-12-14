package com.example.android.nadris

import android.R
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SignupStudentViewModel : ViewModel() {

    var firstname : String = String()
    var lastname : String = String()
    var email : MutableLiveData<String> = MutableLiveData<String>()
    var password:String = String()
    //val arrayAdapter= ArrayAdapter(requireContext(),R.layout.list_item,gender)

}