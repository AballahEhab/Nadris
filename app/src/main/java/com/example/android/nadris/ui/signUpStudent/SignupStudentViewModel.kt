package com.example.android.nadris.ui.signUpStudent

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.PasswordError
import com.example.android.nadris.repository.Repository
import javax.inject.Inject


class SignupStudentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

//    val userdata:LiveData<UserData> =


    var firstname:String = ""
    var lastname:String = ""
    var email:String = ""
    var password1:String = ""
    var password2:String = ""
    var phone:String = ""
    var gender:String = "" // todo:should be modified to get the value of the choosed item not the shown string
    var grade:String = ""  // todo:should be modified to get the value of the choosed item not the shown string

    private var _firstnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val firstnameHaveError get() = _firstnameHaveError
    private var _lastnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val lastnameHaveError get() = _lastnameHaveError
    private var _emailHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError
    private var _password1HaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError
    private var _passwordNotMatch :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    var passwordErrorType :PasswordError? = null
    val passwordNotMatch get() = _passwordNotMatch
    private var _phoneHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val phoneHaveError get() = _phoneHaveError
    private var _ganderHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val ganderHaveError get() = _ganderHaveError
    private var _gradeHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val gradeHaveError get() = _gradeHaveError

    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

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
    fun validateGrade(){
        _gradeHaveError.value = grade.isEmpty()

    }
    fun onSignUpClicked(){
        validFirstName()
        validLastName()
        validEmail()
        validPassword1()
        matchPassword1ToPassword2()
        validPhone()
        validateGender()
        validateGrade()

        val isDataNotValid = _firstnameHaveError.value!!
                ||_lastnameHaveError.value!!
                ||_emailHaveError.value !!
                ||_password1HaveError.value!!
                ||_passwordNotMatch.value!!
                ||_phoneHaveError.value!!
                ||_ganderHaveError.value!!
                || _gradeHaveError.value!!

        if (!isDataNotValid){
            //todo: send data to API
            _navigateToHomeScreen.value = true
        }


    }


}

