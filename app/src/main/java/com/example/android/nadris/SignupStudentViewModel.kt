package com.example.android.nadris

import android.R
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern


class SignupStudentViewModel : ViewModel() {

    var firstname : MutableLiveData<String> = MutableLiveData<String>()
    var lastname : MutableLiveData<String> = MutableLiveData<String>()
    var email : MutableLiveData<String> = MutableLiveData<String>()
    var password1:MutableLiveData<String> = MutableLiveData<String>()
    var password2:MutableLiveData<String> = MutableLiveData<String>()
    var phone:MutableLiveData<String> = MutableLiveData<String>()
    var gander:MutableLiveData<String> = MutableLiveData<String>()
    var grade : MutableLiveData<String> = MutableLiveData<String>()

    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:MutableLiveData<Boolean> = MutableLiveData( false)
    val navigateToCreateAccount :LiveData<Boolean> get() = _navigateToCreateAccount

    private var _showWrongAccountCredentialsDialog :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showWrongAccountCredentialsDialog get() = _showWrongAccountCredentialsDialog
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

    fun onLoginClicked(){
        _showWrongAccountCredentialsDialog.value = false
        if (email.value?.isNotEmpty() == true && password1.value?.isNotEmpty() == true){
            if(email.value!!.trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())){
                //todo:send the data to the api
                _navigateToHomeScreen.value = true
                return
            }
    }

        _showWrongAccountCredentialsDialog.value = true
    }

//    fun singupDone(){
//        _navigateToHomeScreen.value = false
//    }
//
//    fun onCreateAccountClicked(){
//       _navigateToCreateAccount.value = true
//    }
//
//    fun navigationToCreateAccountDone(){
//        _navigateToCreateAccount.value = false
//    }
//

}