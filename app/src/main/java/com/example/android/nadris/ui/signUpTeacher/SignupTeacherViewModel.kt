package com.example.android.nadris.ui.signUpTeacher

import android.util.Patterns
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.PasswordError
import com.example.android.nadris.repository.Repository
import javax.inject.Inject

class SignupTeacherViewModel @Inject constructor(val repository: Repository) : ViewModel() {


//    var firstname : MutableLiveData<String> = MutableLiveData<String>()
//    var lastname : MutableLiveData<String> = MutableLiveData<String>()
//    var email : MutableLiveData<String> = MutableLiveData<String>()
//    var password1: MutableLiveData<String> = MutableLiveData<String>()
//    var password2: MutableLiveData<String> = MutableLiveData<String>()
//    var phone: MutableLiveData<String> = MutableLiveData<String>()
//    var gander: MutableLiveData<String> = MutableLiveData<String>()
//    var subjects : MutableLiveData<String> = MutableLiveData<String>()
//    var collage : MutableLiveData<String> = MutableLiveData<String>()
//    var universty : MutableLiveData<String> = MutableLiveData<String>()

    var firstname:String = ""
    var lastname:String = ""
    var email:String = ""
    var password1:String = ""
    var password2:String = ""
    var phone:String = ""
    var universty:String = "" // todo:should be modified to get the value of the choosed item not the shown string
    var collage:String = "" // todo:should be modified to get the value of the choosed item not the shown string
    var gender:String = "" // todo:should be modified to get the value of the choosed item not the shown string
    var subjects:String = ""  // todo:should be modified to get the value of the choosed item not the shown string

    private var _firstnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val firstnameHaveError get() = _firstnameHaveError
    private var _lastnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val lastnameHaveError get() = _lastnameHaveError
    private var _emailHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError
    private var _password1HaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError
    private var _passwordNotMatch :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    var passwordErrorType : PasswordError? = null
    val passwordNotMatch get() = _passwordNotMatch
    private var _phoneHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val phoneHaveError get() = _phoneHaveError
    private var _ganderHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val ganderHaveError get() = _ganderHaveError
    private var _subjectsHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val subjectsHaveError get() = _subjectsHaveError

    private var _collageHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val collageHaveError get() = _collageHaveError
    private var _universtyHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val universtyHaveError get() = _universtyHaveError



    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val  navigateToHomeScreen get() = _navigateToHomeScreen


    fun validFirstName(){
        _firstnameHaveError.value = firstname.isEmpty()
    }
    fun validLastName(){
        _lastnameHaveError.value = lastname.isEmpty()
    }
    fun validEmail(){
        _emailHaveError.value = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun validPassword1(){

        passwordErrorType = null

        if (password1.length < 8)
        {
            passwordErrorType = PasswordError.SHORT_PASSWORD
        }
        if (!password1.matches(".*[A-Z].*".toRegex()))
        {
            passwordErrorType = PasswordError.NOT_CONTAIN_UPPERCASE
        }
        if (!password1.matches(".*[a-z].*".toRegex()))
        {
            passwordErrorType = PasswordError.NOT_CONTAIN_LOWER_CASE
        }
        if (!password1.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            passwordErrorType = PasswordError.NOT_CONTAIN_SPECIAL_CHARACTER
        }
        _password1HaveError.value = passwordErrorType != null

    }
    fun matchPassword1ToPassword2(){
        _passwordNotMatch.value = password2.isEmpty()|| password1 != password2
    }
    fun validPhone(){
        _phoneHaveError.value = !phone.matches(Patterns.PHONE.toRegex())
    }
    fun validateGender(){
        _ganderHaveError.value = gender.isEmpty()
    }
    fun validateSubjec(){

        _subjectsHaveError.value = subjects.isEmpty()

    }
    fun validateCollegeFeild(){
        _collageHaveError.value = collage.isEmpty()
    }
    fun validateUniversityFeildInput(){
        _universtyHaveError.value = universty.isEmpty()
    }
    fun onSignUpClicked(){
        validFirstName()
        validLastName()
        validEmail()
        validPassword1()
        matchPassword1ToPassword2()
        validPhone()
        validateGender()
        validateSubjec()
        validateUniversityFeildInput()
        validateCollegeFeild()

        val isDataNotValid = _firstnameHaveError.value!!
                ||_lastnameHaveError.value!!
                ||_emailHaveError.value !!
                ||_password1HaveError.value!!
                ||_passwordNotMatch.value!!
                ||_phoneHaveError.value!!
                ||_ganderHaveError.value!!
                || _subjectsHaveError.value!!
                ||_collageHaveError.value!!
                ||_universtyHaveError.value!!

        if (!isDataNotValid){
            //todo: send data to API
            _navigateToHomeScreen.value = true
        }


    }


}