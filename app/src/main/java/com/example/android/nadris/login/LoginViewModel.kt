package com.example.android.nadris.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.network.APIInstance
import com.example.android.nadris.network.CreateAccountData
import com.example.android.nadris.network.LoginAccountModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

     var email : String = String()

     var password:String = String()


    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:MutableLiveData<Boolean> = MutableLiveData( false)
    val navigateToCreateAccount :LiveData<Boolean> get() = _navigateToCreateAccount

    private var _showWrongAccountCredentialsDialog :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showWrongAccountCredentialsDialog get() = _showWrongAccountCredentialsDialog





     fun onLoginClicked(){
        _showWrongAccountCredentialsDialog.value = false
        if (email.isNotEmpty() && password.isNotEmpty()){
            if(email.trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())){
                //todo:send the data to the api
//                _navigateToHomeScreen.value = true
//                return
            }
            }

         viewModelScope.launch{

             val responce = APIInstance.API.createAccount(CreateAccountData(firstName = "first name", userName = "myemail@email.com", lastName = "last name", email = "email@email.com", password = "AaBbSsRr#!@123", phoneNumber = "1010101010", gender = "other", type = "student", grade = 15, university = "bakinam", colleage = "habdasa"))
             Log.v("responce","the responce is: "+responce.body())
         }
//            _showWrongAccountCredentialsDialog.value = true
    }

    fun loginDone(){
        _navigateToHomeScreen.value = false
    }

    fun onCreateAccountClicked(){
        _navigateToCreateAccount.value = true
    }

    fun navigationToCreateAccountDone(){
        _navigateToCreateAccount.value = false
    }

    fun OnLoginByGoogleClicked(){
        // todo: use the api to lgin by google
        // if things done set navigate to home screen to true

        _navigateToHomeScreen.value = true
    }

    fun OnLoginByFacebookClicked(){
        // todo: use the api to lgin by facebook
        // if things done set navigate to home screen to true

        _navigateToHomeScreen.value = true
    }

    fun OnLoginByTwitterClicked(){
        // todo: use the api to lgin by Twitter
        // if things done set navigate to home screen to true

        _navigateToHomeScreen.value = true
    }

}