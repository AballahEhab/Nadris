package com.example.android.nadris.login

import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

     var email : String = String()

     var password:String = String()


    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:MutableLiveData<Boolean> = MutableLiveData( false)
    val navigateToCreateAccount :LiveData<Boolean> get() = _navigateToCreateAccount

    private var _showWrongAccountCredentialsDialog :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showWrongAccountCredentialsDialog get() = _showWrongAccountCredentialsDialog



//    fun onLoginClicked(){
//        _showWrongAccountCredentialsDialog.value = false
//        if (email.isNotEmpty() && password.isNotEmpty()){
//            if(email.trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())){
//                _navigateToHomeScreen.value = true
//                return
//            }
//            }
//            _showWrongAccountCredentialsDialog.value = true
//    }
//
//    fun loginDone(){
//        _navigateToHomeScreen.value = false
//    }
//
//    fun onCreateAccountClicked(){
//        _navigateToCreateAccount.value = true
//    }
//
//    fun navigationToCreateAccountDone(){
//        _navigateToCreateAccount.value = false
//    }
//
//    fun OnLoginByGoogleClicked(){
//        // if things done set navigate to home screen to true
//
//        _navigateToHomeScreen.value = true
//    }
//
//    fun OnLoginByFacebookClicked(){
//        // if things done set navigate to home screen to true
//
//        _navigateToHomeScreen.value = true
//    }
//
//    fun OnLoginByTwitterClicked(){
//        // if things done set navigate to home screen to true
//
//        _navigateToHomeScreen.value = true
//    }

}