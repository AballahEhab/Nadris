package com.example.android.nadris.ui.signUpStudent

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.PasswordError
import com.example.android.nadris.network.CreateStudentAccountDataModelModel
import com.example.android.nadris.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupStudentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

//    val userdata:LiveData<UserData> =


    var firstname:String = ""
    var lastname:String = ""
    var email:String = ""
    var password1:String = ""
    var password2:String = ""
    var phone:String = ""

    private var _gender :MutableLiveData<String> =  MutableLiveData<String>("")
    val gender get() = _gender

    private var _grade :MutableLiveData<String> =  MutableLiveData<String>("")
    val grade get() = _grade

    var genderId:Int = 1
    var gradeId:Int = 1

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

    private var _showIndicator :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

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
        if (!password1.matches(".*[!@#\$%^&+=].*".toRegex()))
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
    fun validateGrade(){
        _gradeHaveError.value = grade.value?.isEmpty()

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
            enableProgressBar()
            viewModelScope.launch {
                val response = repository.registerNewStudentAccount(CreateStudentAccountDataModelModel(
                    firstname,lastname,email,password1,phone, genderId,gradeId))
                response.collect {
                    it?.handleRepoResponse(
                        onLoading= {},
                        onError= {
                            disableProgressBar()
                            _errorMessageVisibility.value = true
                            _errorMessage.value = it.error!!
                            Log.v("ErrorResponce", _errorMessage.value!!)
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

