package com.example.android.nadris.ui.loginActivity.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.NetworkModelsMapper
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( val repository:Repository) : ViewModel() {


    var email:MutableLiveData<String> = MutableLiveData("")
    var emailErrorMessage :MutableLiveData<String?> =  MutableLiveData<String?>()


    var password:MutableLiveData<String> = MutableLiveData("")
    var passwordErrorMessage :MutableLiveData<String?> =  MutableLiveData<String?>()





    private var _showIndicator :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator
    private var _loginRequestErrorMessage :MutableLiveData<String> =  MutableLiveData<String>("")
    val loginRequestErrorMessage get() = _loginRequestErrorMessage
    private var _errorMessageVisibility :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility


    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:MutableLiveData<Boolean> = MutableLiveData( false)
    val navigateToCreateAccount :LiveData<Boolean> get() = _navigateToCreateAccount







    private fun validateEmail() {

        email.value?.trim()

        var errorFlag = matchEmailPattern(email.value!!)

        checkEmpty(email.value!!)?.let {
            errorFlag = it
        }
         emailErrorMessage.value = getErrorMessage(errorFlag)


    }
    private fun validatePassword(){
        var errorFlag = checkPasswordLength(password.value!!)

        containsSpecialLetter(password.value!!)?.let {
            errorFlag = it
        }
        containsLowerLetter(password.value!!)?.let {
                    errorFlag = it
                }
        containsUpperLetter(password.value!!)?.let {
                    errorFlag = it
                }

        checkEmpty(password.value!!)?.let {
            errorFlag = it
        }
        passwordErrorMessage.value = getErrorMessage(errorFlag)
    }

    fun onLoginClicked(){
        disableErrorMessage()
        validateEmail()
        validatePassword()
        if((emailErrorMessage.value == null)&&(passwordErrorMessage.value == null)){
            enableProgressBar()
            viewModelScope.launch {
                val response = repository.login(LoginAccountModel(email.value!!, password.value!!))
                response.collect {
                    it?.handleRepoResponse(
                        onLoading= {},
                        onError= {
                            disableProgressBar()
                            enableErrorMessage()
                            _loginRequestErrorMessage.value = it.error!!
                        },
                        onSuccess= {
                            disableProgressBar()
                            Log.v("responceTag", it.data?.Token!!)
                            NadrisApplication.userData =it.data
                            navigateToHomeScreen()

//                            val user = NadrisApplication.instance!!.userData
//                            user?.Token?.let { it1 -> getPosts(it1) }
                        },
                    )

                }
            }
        }
    }


     fun getUser(){
        viewModelScope.launch{
            repository.getUser().collect {
                NadrisApplication.userData = it
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

    fun onCreateAccountClicked(){
        _navigateToCreateAccount.value = true
    }

    fun navigationToCreateAccountDone(){
        _navigateToCreateAccount.value = false
    }

    fun onLoginByGoogleClick(){
 //       _navigateToHomeScreen.value = true
    }

    fun onLoginByFacebookClick(){
//        _navigateToHomeScreen.value = true
    }

}