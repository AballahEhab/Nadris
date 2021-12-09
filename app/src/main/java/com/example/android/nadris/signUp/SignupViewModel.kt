package com.example.android.nadris.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    private var _navigate_to_teacher_signUp:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_teacher_signUp:MutableLiveData<Boolean> get() = _navigate_to_teacher_signUp

    private var _navigate_to_student_signUp:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_student_signUp:MutableLiveData<Boolean> get() = _navigate_to_student_signUp

    private var _navigate_to_login:MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val navigate_to_login:MutableLiveData<Boolean> get() = _navigate_to_login



    fun navigate_to_teacher_signUp(){
        _navigate_to_teacher_signUp.value = true
    }

    fun navigation_to_teacher_signUp_done(){
        _navigate_to_teacher_signUp.value = false
    }

    fun navigate_to_student_signUp(){
        _navigate_to_student_signUp.value = true
    }

    fun navigation_to_student_signUp_done(){
        _navigate_to_student_signUp.value = false
    }

    fun navigate_to_login(){
        _navigate_to_login.value = true
    }

    fun navigation_to_login_done(){
        _navigate_to_login.value = false
    }

}