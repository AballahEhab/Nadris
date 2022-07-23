package com.example.android.nadris

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.Result
import com.example.android.nadris.util.checkPassword
import com.example.android.nadris.util.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _changePasswordResult: MutableLiveData<Result<Boolean>> = MutableLiveData()
    val  changePasswordResult:LiveData<Result<Boolean>> get() = _changePasswordResult


    //old password
    var password : MutableLiveData<String> = MutableLiveData("")
    var passwordErrorMessage: MutableLiveData<String?> = MutableLiveData<String?>()


   // validate Old Password
    private fun validateOldPassword() {
        val errorFlag = checkPassword(password.value!!)
        passwordErrorMessage.value = getErrorMessage(errorFlag)
    }

//new password
    var password1: String = ""
    var password2: String = ""


    private var _password1HaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError

    private var _passwordNotMatch: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var passwordErrorType: InputError? = null
    val passwordNotMatch get() = _passwordNotMatch

    private var _errorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val errorMessage get() = _errorMessage

    private var _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility

    fun validPassword1() {
        passwordErrorType = checkPassword(password1)
        _password1HaveError.value = passwordErrorType != null
    }

    fun matchPassword1ToPassword2() {
        _passwordNotMatch.value = password2.isEmpty() || password1 != password2
    }




    private fun enableErrorMessage() {
        _errorMessageVisibility.postValue(true)
    }

    private fun disableErrorMessage() {
        _errorMessageVisibility.value = false
    }



    // send old password and new password to api and get result
    // call in on cliclk save botton

//    fun onSaveChangeisClicked() {
//
//        validateOldPassword()
//        validPassword1()
//        matchPassword1ToPassword2()
//
//        if ((passwordErrorMessage.value == null)&&(passwordErrorType == null) &&!(_password1HaveError) && !(_passwordNotMatch))
//            viewModelScope.launch(Dispatchers.IO) {
//                repository.signInWithEmailAndPassword(newpassword.value!!,
//                    password.value!!).collect {
//                    _changePasswordResult.postValue(it)
//                }
//            }
//    }





}