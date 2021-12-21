package com.example.android.nadris

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupTeacherViewModel : ViewModel() {
    var firstname : MutableLiveData<String> = MutableLiveData<String>()
    var lastname : MutableLiveData<String> = MutableLiveData<String>()
    var email : MutableLiveData<String> = MutableLiveData<String>()
    var password1: MutableLiveData<String> = MutableLiveData<String>()
    var password2: MutableLiveData<String> = MutableLiveData<String>()
    var phone: MutableLiveData<String> = MutableLiveData<String>()
    var gander: MutableLiveData<String> = MutableLiveData<String>()
    var subjects : MutableLiveData<String> = MutableLiveData<String>()
    var collage : MutableLiveData<String> = MutableLiveData<String>()
    var universty : MutableLiveData<String> = MutableLiveData<String>()
    fun validFirstName(): String?{
        val firstName= firstname.value
        if(firstName?.isNotEmpty()==true){
            return " "
        }
        return "مطلوب"
    }
    fun validLastName(): String?{
        val lastName= lastname.value
        if(lastName?.isNotEmpty()==true){
            return " "
        }
        return "مطلوب"
    }
    fun validEmail(): String? {
        val emailText=email.value
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return " Invalid Email Address"
        }
        return " "
    }
    fun validPassword1():String?{
        val passwordText= password1.value
        if (passwordText!!.length < 8)
        {
            return "Minimum 8 Character password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Characters"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Characters"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Characters(@#\$%^&+=)"
        }
        return " "
    }
    fun validPassword2():String?{
        if(password2.value?.isEmpty()== true){
            return "required"
        }
        if(password2.value!=password1.value ){
            return "Password does not match"
        }
        return " "
    }
    fun validPhone():String?{
        val phoneText= phone.value
        if (phoneText?.length != 11)
        {
            return "must be 11 digit"
        }
        if (!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all digit Number"
        }
        return " "
    }
}