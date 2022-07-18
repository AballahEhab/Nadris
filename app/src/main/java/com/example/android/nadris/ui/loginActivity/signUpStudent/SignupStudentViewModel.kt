package com.example.android.nadris.ui.loginActivity.signUpStudent

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.InputError
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.firebase.dtos.Grade
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
SignupStudentViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    //the value will valid in method
    var firstname: String = ""
    var lastname: String = ""
    var email: String = ""
    var password1: String = ""
    var password2: String = ""
    var phone: String = ""

    var genderList: ArrayList<String> = ArrayList()
    val selectedGender: MutableLiveData<String> = MutableLiveData<String>("")

    // this list return from api fir base
    var gradesList: MutableLiveData<List<Grade>> = MutableLiveData<List<Grade>>()
    var selectedGrade: MutableLiveData<String> = MutableLiveData<String>()



    //variable is tru when have error and show in errorEnabled  set value in method valid
    //variable indicator error

    private var _firstnameHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val firstnameHaveError get() = _firstnameHaveError

    private var _lastnameHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val lastnameHaveError get() = _lastnameHaveError

    private var _emailHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError

    private var _password1HaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError

    private var _passwordNotMatch: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var passwordErrorType: InputError? = null
    val passwordNotMatch get() = _passwordNotMatch

    private var _phoneHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val phoneHaveError get() = _phoneHaveError

    private var _ganderHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val ganderHaveError get() = _ganderHaveError

    private var _gradeHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val gradeHaveError get() = _gradeHaveError

    private var _showIndicator: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _navigateToHomeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen: LiveData<Boolean> get() = _navigateToHomeScreen

    private var _errorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val errorMessage get() = _errorMessage

    private var _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility



    //method valid call afterTextChanged

    fun validFirstName() {
        _firstnameHaveError.value = firstname.isEmpty() || (firstname.length < 3)
    }

    fun validLastName() {
        _lastnameHaveError.value = lastname.isEmpty() || (lastname.length < 3)
    }

    fun validEmail() {
        _emailHaveError.value = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validPassword1() {
        passwordErrorType = checkPassword(password1)
        _password1HaveError.value = passwordErrorType != null
    }

    fun matchPassword1ToPassword2() {
        _passwordNotMatch.value = password2.isEmpty() || password1 != password2
    }

    fun validPhone() {
        _phoneHaveError.value = !phone.matches(Patterns.PHONE.toRegex())
    }

    fun validateGender() {
        _ganderHaveError.value = selectedGender.value?.isEmpty()
    }

    fun validateGrade() {
        _gradeHaveError.value = selectedGrade.value?.isEmpty() ?: true

    }





//when click on sinup botton
    fun onSignUpClicked() {
        disableErrorMessage()
        validFirstName()
        validLastName()
        validEmail()
        validPassword1()
        matchPassword1ToPassword2()
        validPhone()
        validateGender()
        validateGrade()

        val isDataNotValid = _firstnameHaveError.value!!
                || _lastnameHaveError.value!!
                || _emailHaveError.value!!
                || _password1HaveError.value!!
                || _passwordNotMatch.value!!
                || _phoneHaveError.value!!
                || _ganderHaveError.value!!
                || _gradeHaveError.value!!

    //todo
        if (!isDataNotValid) {
            enableProgressBar()
            val genderType = genderList.indexOf(selectedGender.value) == 0

            val grade =
                gradesList.value?.find {
                    it.name_ar == selectedGrade.value
                }?.gradeReference
            Log.i("sectionId", grade?.path!!)
            viewModelScope.launch(Dispatchers.IO) {
                val result = repository
                    .createNewUser(
                        User(
                            firstName = firstname,
                            lastName = lastname,
                            email = email,
                            gender = genderType,
                            grade = grade,
                            phoneNumber = phone,
                            isATeacher = false,
                        ), password1)

                result.handleRepoResponse(
                    onLoading = {},
                    onError = {
                        disableProgressBar()
                        enableErrorMessage()
                        _errorMessage.value = result.error!!
                        Log.v("ErrorResponce", _errorMessage.value!!)
                    },
                    onSuccess = {
                        disableProgressBar()
                        NadrisApplication.currentDatabaseUser = result.data
                        navigateToHomeActivity()
                    },
                )
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
        _navigateToHomeScreen.postValue(true)
    }

    fun navigationToHomeActivityDone() {
        _navigateToHomeScreen.value = false
    }





// get Grades list and pass to gradesList vriable
    fun getGrades() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getGrades()
            result.handleRepoResponse(
                onLoading = {
                },
                onError = {
                },
                onSuccess = {
                    gradesList.postValue(result.data)
                }
            )
        }
    }
}

