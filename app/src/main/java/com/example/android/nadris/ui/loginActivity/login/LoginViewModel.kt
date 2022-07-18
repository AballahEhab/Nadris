package com.example.android.nadris.ui.loginActivity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.database.models.DatabaseUser
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var email: MutableLiveData<String> = MutableLiveData("")
    var emailErrorMessage : MutableLiveData<String?> = MutableLiveData<String?>()

    var password : MutableLiveData<String> = MutableLiveData("")
    var passwordErrorMessage: MutableLiveData<String?> = MutableLiveData<String?>()

    private var _navigateToCreateAccount: MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToCreateAccount: LiveData<Boolean> get() = _navigateToCreateAccount

    private var _loginResult: MutableLiveData<Result<DatabaseUser>> = MutableLiveData()
    val loginResult: LiveData<Result<DatabaseUser>> get() = _loginResult


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
        validateEmail()
        validatePassword()
        if ((emailErrorMessage.value == null) && (passwordErrorMessage.value == null))
            viewModelScope.launch(Dispatchers.IO) {
                repository.signInWithEmailAndPassword(email.value!!,
                    password.value!!).collect {
                    _loginResult.postValue(it)
                }
            }
    }



    fun onCreateAccountClicked() {
        _navigateToCreateAccount.value = true
    }

    fun navigationToCreateAccountDone() {
        _navigateToCreateAccount.value = false
    }




    /** TODO: to be enabled when adding login with facebook and google*/
    fun onLoginByGoogleClick() {
//    _navigateToHomeScreen.value = true
    }

    fun onLoginByFacebookClick() {
//    _navigateToHomeScreen.value = true
    }

}