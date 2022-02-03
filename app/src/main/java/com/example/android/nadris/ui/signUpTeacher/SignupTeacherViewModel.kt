package com.example.android.nadris.ui.signUpTeacher

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.PasswordError
import com.example.android.nadris.network.CreateStudentAccountDataModelModel
import com.example.android.nadris.network.CreateTeacherAccountDataModelModel
import com.example.android.nadris.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
//    var university : MutableLiveData<String> = MutableLiveData<String>()

    var firstname:String = ""
    var lastname:String = ""
    var email:String = ""
    var password1:String = ""
    var password2:String = ""
    var phone:String = ""


    private var _university :MutableLiveData<String> =  MutableLiveData<String>("")
    val university get() = _university

    private var _collage :MutableLiveData<String> =  MutableLiveData<String>("")
    val collage get() = _collage

    private var _subjects :MutableLiveData<String> =  MutableLiveData<String>("")
    val subjects get() = _subjects

    private var _gender :MutableLiveData<String> =  MutableLiveData<String>("")
    val gender get() = _gender
    var genderId:Int = 1

    private var _firstnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val firstnameHaveError get() = _firstnameHaveError

    private var _lastnameHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val lastnameHaveError get() = _lastnameHaveError

    private var _emailHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError

    private var _password1HaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError

    var passwordErrorType : PasswordError? = null

    private var _passwordNotMatch :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val passwordNotMatch get() = _passwordNotMatch

    private var _phoneHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val phoneHaveError get() = _phoneHaveError

    private var _ganderHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val ganderHaveError get() = _ganderHaveError

    private var _subjectsHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val subjectsHaveError get() = _subjectsHaveError

    private var _collageHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val collageHaveError get() = _collageHaveError

    private var _universityHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val universityHaveError get() = _universityHaveError

    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val  navigateToHomeScreen get() = _navigateToHomeScreen

    private var _showIndicator :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _errorMessage :MutableLiveData<String> =  MutableLiveData<String>("")
    val errorMessage get() = _errorMessage

    private var _errorMessageVisibility :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility


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
        _ganderHaveError.value = gender.value?.isEmpty()
    }
    fun validateSubject(){

        _subjectsHaveError.value = subjects.value?.isEmpty()

    }
    fun validateCollegeField(){
        _collageHaveError.value = collage.value?.isEmpty()
    }
    fun validateUniversityFieldInput(){
        _universityHaveError.value = university.value?.isEmpty()
    }
    fun onSignUpClicked(){
        validFirstName()
        validLastName()
        validEmail()
        validPassword1()
        matchPassword1ToPassword2()
        validPhone()
        validateGender()
        validateSubject()
        validateUniversityFieldInput()
        validateCollegeField()

        val isDataNotValid = _firstnameHaveError.value!!
                ||_lastnameHaveError.value!!
                ||_emailHaveError.value !!
                ||_password1HaveError.value!!
                ||_passwordNotMatch.value!!
                ||_phoneHaveError.value!!
                ||_ganderHaveError.value!!
                || _subjectsHaveError.value!!
                ||_collageHaveError.value!!
                ||_universityHaveError.value!!

        if (!isDataNotValid){
            disableErrorMessage()
            enableProgressBar()
            viewModelScope.launch {
                val response = repository.registerNewTeacherAccount(
                    CreateTeacherAccountDataModelModel(
                    firstname,lastname,email,password1,phone, genderId,university.value,collage.value)
                )
                response.collect{

                    it.handleRepoResponse(
                        onLoading= {},
                        onError= {
                            disableProgressBar()
                            enableErrorMessage()
                            _errorMessage.value = it.error!!
                        },
                        onSuccess= {
                            disableProgressBar()
                            navigateToHomeScreen()
                        },
                    )

                }
            }
        }

    }

    private fun enableErrorMessage(){
        _errorMessageVisibility.value = true

    }

    private fun disableErrorMessage(){
        _errorMessageVisibility.value = false
    }

    private fun enableProgressBar(){
        _showIndicator.value = true
    }

    private fun disableProgressBar(){
        _showIndicator.value = false
    }
    private fun navigateToHomeScreen(){
        _navigateToHomeScreen.value = true
    }
    fun navigationAfterSuccessfulLoginDone(){
        _navigateToHomeScreen.value = false
    }




}