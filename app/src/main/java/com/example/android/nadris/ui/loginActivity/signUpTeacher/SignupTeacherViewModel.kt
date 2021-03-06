package com.example.android.nadris.ui.loginActivity.signUpTeacher

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.InputError
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.network.firebase.dtos.College
import com.example.android.nadris.network.firebase.dtos.University
import com.example.android.nadris.network.firebase.dtos.User
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.util.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupTeacherViewModel @Inject constructor(val repository: Repository) : ViewModel() {


    var firstname: String = ""
    var lastname: String = ""
    var email: String = ""
    var password1: String = ""
    var password2: String = ""
    var phone: String = ""

    //    var university: String = ""
//    var collage: String = ""
    var universities: MutableLiveData<List<University>> = MutableLiveData<List<University>>()
    var colleges: MutableLiveData<List<College>> = MutableLiveData<List<College>>()
    private var selectedUniversity: MutableLiveData<String> = MutableLiveData<String>()
    var selectedCollege: MutableLiveData<String> = MutableLiveData<String>()


    private var _selectedSubject: MutableLiveData<String> = MutableLiveData<String>("")
    val selectedSubject get() = _selectedSubject
    var genderList: ArrayList<String> = ArrayList()
    private var _gender: MutableLiveData<String> = MutableLiveData<String>("")
    val gender get() = _gender

    private var _firstnameHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val firstnameHaveError get() = _firstnameHaveError

    private var _lastnameHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val lastnameHaveError get() = _lastnameHaveError

    private var _emailHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError

    private var _password1HaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val password1HaveError get() = _password1HaveError

    var passwordErrorType: InputError? = null

    private var _passwordNotMatch: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val passwordNotMatch get() = _passwordNotMatch

    private var _phoneHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val phoneHaveError get() = _phoneHaveError

    private var _ganderHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val ganderHaveError get() = _ganderHaveError

    private var _subjectsHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val subjectsHaveError get() = _subjectsHaveError

    private var _collageHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val collageHaveError get() = _collageHaveError

    private var _universityHaveError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val universityHaveError get() = _universityHaveError

    private var _navigateToHomeScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen get() = _navigateToHomeScreen

    private var _showIndicator: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val showIndicator get() = _showIndicator

    private var _errorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    val errorMessage get() = _errorMessage

    private var _errorMessageVisibility: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val errorMessageVisibility get() = _errorMessageVisibility

    fun setSelectedUniversity(university: String) {
        selectedUniversity.value = university
        getColleges()
    }

    fun getSelectedUniversity(): String? {
        return selectedUniversity.value
    }

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
        _ganderHaveError.value = gender.value?.isEmpty()
    }

    fun validateSubject() {
        _subjectsHaveError.value = selectedSubject.value?.isEmpty()
    }

    fun validateCollegeField() {
        _collageHaveError.value = selectedCollege.value.isNullOrEmpty()
    }

    fun validateUniversityFieldInput() {
        _universityHaveError.value = selectedUniversity.value.isNullOrEmpty()
    }

    fun onSignUpClicked() {
        validFirstName()
        validLastName()
        validEmail()
        validPassword1()
        matchPassword1ToPassword2()
        validPhone()
        validateGender()
        validateSubject()
        validateUniversityFieldInput()
        validateCollegeField()

        //the data is not valid if on or more have true
        val isDataNotValid = _firstnameHaveError.value!!
                || _lastnameHaveError.value!!
                || _emailHaveError.value!!
                || _password1HaveError.value!!
                || _passwordNotMatch.value!!
                || _phoneHaveError.value!!
                || _ganderHaveError.value!!
                || _subjectsHaveError.value!!
                || _collageHaveError.value!!
                || _universityHaveError.value!!

        if (!isDataNotValid) {
            disableErrorMessage()
            enableProgressBar()
            val universityDocRef =
                universities.value!!.find { it.name_ar == selectedUniversity.value }!!.docRef
            val collegeDocRef =
                colleges.value!!.find { it.name_ar == selectedCollege.value }!!.docRef
            val genderId = genderList.indexOf(gender.value) == 0

//            Log.i("test", firstname + lastname + email + password1 + phone + genderId + universityId + collegeId)

            viewModelScope.launch(Dispatchers.IO) {
                val result = repository
                    .createNewUser(
                        User(
                            firstName = firstname,
                            lastName = lastname,
                            email = email,
                            gender = genderId,
                            phoneNumber = phone,
                            isATeacher = true,
                            university = universityDocRef,
                            college = collegeDocRef
                        ), password1)

                result.handleRepoResponse(
                        onLoading = {},
                        onError = {
                            disableProgressBar()
                            enableErrorMessage()
                            _errorMessage.value = result.error!!
                        },
                        onSuccess = {
                            disableProgressBar()
                            NadrisApplication.currentDatabaseUser = result.data
                            navigateToHomeScreen()
                        },
                    )
            }
        }

    }

    private fun enableErrorMessage() {
        _errorMessageVisibility.value = true

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

    private fun navigateToHomeScreen() {
        _navigateToHomeScreen.postValue(true)
    }

    fun navigationAfterSuccessfulLoginDone() {
        _navigateToHomeScreen.value = false
    }

    fun getUniversities() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllUniversities()
            result.handleRepoResponse(
                onLoading = {

                },
                onError = {

                },
                onSuccess = {
                    universities.postValue(result.data)
                }
            )
        }
    }

    private fun getColleges() {

        val universityId =
            universities.value!!.find { it.name_ar == selectedUniversity.value }!!.docRef
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCollegeForAUniversity(universityId!!)
            result.handleRepoResponse(
                onLoading = {
                },
                onError = {
                    Log.v("fjaslfkd0", result.error!!)
                },
                onSuccess = {
                    colleges.postValue(result.data)
                },
            )
        }
    }


}