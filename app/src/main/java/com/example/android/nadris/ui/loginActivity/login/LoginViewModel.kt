package com.example.android.nadris.ui.loginActivity.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.checkEmpty
import com.example.android.nadris.util.checkPassword
import com.example.android.nadris.util.getErrorMessage
import com.example.android.nadris.util.matchEmailPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: Repository) : ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData("")
    var emailErrorMessage: MutableLiveData<String?> = MutableLiveData<String?>()

    var password: MutableLiveData<String> = MutableLiveData("")
    var passwordErrorMessage: MutableLiveData<String?> = MutableLiveData<String?>()

    private var _showIndicator: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _loginRequestErrorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val loginRequestErrorMessage get() = _loginRequestErrorMessage

    private var _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility

    private var _navigateToHomeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen: LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount: MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToCreateAccount: LiveData<Boolean> get() = _navigateToCreateAccount


    private fun validateEmail() {

        email.value?.trim()

        var errorFlag = matchEmailPattern(email.value!!)

        checkEmpty(email.value!!)?.let {
            errorFlag = it
        }
        emailErrorMessage.value = getErrorMessage(errorFlag)
    }

    private fun validatePassword() {
        val errorFlag = checkPassword(password.value!!)
        passwordErrorMessage.value = getErrorMessage(errorFlag)
    }

    fun onLoginClicked() {
        disableErrorMessage()
        validateEmail()
        validatePassword()
        if ((emailErrorMessage.value == null) && (passwordErrorMessage.value == null)) {

            enableProgressBar()

            viewModelScope.launch(Dispatchers.IO) {

                val result = repository.signInWithEmailAndPassword(email.value!!, password.value!!)

                result.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        _loginRequestErrorMessage.postValue( result.error!!)
                        enableErrorMessage()
                    },
                    onSuccess = {
                        NadrisApplication.currentUserLocalData = result.data
                        Log.v("loginViewModel", result.data.toString())
                        navigateToHomeActivity()
                    },
                )
                disableProgressBar()

            }

        }
    }


    private fun enableErrorMessage() {
        _errorMessageVisibility.postValue(true)

    }

    private fun disableErrorMessage() {
        _errorMessageVisibility.value = false
    }


    private fun enableProgressBar() {
        _showIndicator.value = true
    }

    private fun disableProgressBar() {
        _showIndicator.postValue(false)
    }

    private fun navigateToHomeActivity() {
        _navigateToHomeScreen.postValue( true)
    }

    fun navigateToHomeActivityDone() {
        _navigateToHomeScreen.value = false
    }

    fun onCreateAccountClicked() {
        _navigateToCreateAccount.value = true
    }

    fun navigationToCreateAccountDone() {
        _navigateToCreateAccount.value = false
    }

    fun onLoginByGoogleClick() {
        //       _navigateToHomeScreen.value = true
    }

    fun onLoginByFacebookClick() {
//        _navigateToHomeScreen.value = true
    }

}