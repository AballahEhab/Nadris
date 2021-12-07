package com.example.android.nadris.login

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

     var emailOrMobile : String = String()

     var password:String = String()


    private var _navigateToHomeScreen:Boolean = false
    val navigateToHomeScreen :Boolean get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:Boolean = false
    val navigateToCreateAccount :Boolean get() = _navigateToCreateAccount





    fun onLoginClicked(){
        //ToDo: check for email and password if are correct then send for api to check if user registered finally set navigate to home page true
        _navigateToHomeScreen = true

    }
    fun loginDone(){
        _navigateToHomeScreen = false

    }
    fun onCreateAccountClicked(){
        _navigateToCreateAccount = true
    }
    fun navigationToCreateAccountDone(){
        _navigateToCreateAccount = false
    }

    fun OnLoginByGoogleClicked(){
        // todo: use the api to lgin by google
        // if things done set navigate to home screen to true

        _navigateToHomeScreen = true
    }

    fun OnLoginByFacebookClicked(){
        // todo: use the api to lgin by facebook
        // if things done set navigate to home screen to true

        _navigateToHomeScreen = true
    }

    fun OnLoginByTwitterClicked(){
        // todo: use the api to lgin by Twitter
        // if things done set navigate to home screen to true

        _navigateToHomeScreen = true
    }
}