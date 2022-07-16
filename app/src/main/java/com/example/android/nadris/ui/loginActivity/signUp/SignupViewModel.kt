package com.example.android.nadris.ui.loginActivity.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    private var _navigateToTeacherSignUp:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigateToTeacherSignUp:MutableLiveData<Boolean> get() = _navigateToTeacherSignUp

    private var _navigateToStudentSignUp:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigateToStudentSignUp:MutableLiveData<Boolean> get() = _navigateToStudentSignUp

    private var _navigateToLogin:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigateToLogin:MutableLiveData<Boolean> get() = _navigateToLogin


    fun navigateToTeacherSignUp(){
        _navigateToTeacherSignUp.value = true
    }

    fun navigationTeacherSignUpDone(){
        _navigateToTeacherSignUp.value = false
    }

    fun navigateToStudentSignup(){
        _navigateToStudentSignUp.value = true
    }

    fun navigationToStudentSignupDone(){
        _navigateToStudentSignUp.value = false
    }

    fun navigateToLogin(){
        _navigateToLogin.value = true
    }

    fun navigationToLoginDone(){
        _navigateToLogin.value = false
    }
}