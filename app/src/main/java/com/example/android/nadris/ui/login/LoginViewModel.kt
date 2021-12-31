package com.example.android.nadris.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.nadris.network.LoginAccountModel
import com.example.android.nadris.network.asNetworkModel
import com.example.android.nadris.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    var email : String = String()

    var password:String = String()


    private var _navigateToHomeScreen:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigateToHomeScreen :LiveData<Boolean> get() = _navigateToHomeScreen

    private var _navigateToCreateAccount:MutableLiveData<Boolean> = MutableLiveData( false)
    val navigateToCreateAccount :LiveData<Boolean> get() = _navigateToCreateAccount

//    private var _showWrongAccountCredentialsDialog :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
//    val showWrongAccountCredentialsDialog get() = _showWrongAccountCredentialsDialog

    private var _emailHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val emailHaveError get() = _emailHaveError

    private var _passwordHaveError :MutableLiveData<Boolean> =  MutableLiveData<Boolean>(false)
    val passwordHaveError get() = _passwordHaveError




    fun validateEmail() {
        _emailHaveError.value = !email.trim().matches(Patterns.EMAIL_ADDRESS.toRegex())
    }
    fun validatePassword(){
        _passwordHaveError.value = password.isEmpty()
    }

    fun onLoginClicked(){
//        _showWrongAccountCredentialsDialog.value = false
        validateEmail()
        validatePassword()
        if((!_emailHaveError.value!!)&&(!_passwordHaveError.value!!)){
            //todo:send the data to the api
         viewModelScope.launch{

//             val responce = APIInstance.API.createAccount(CreateAccountData(firstName = "first name", userName = "myemail@email.com", lastName = "last name", email = "email@email.com", password = "AaBbSsRr#!@123", phoneNumber = "1010101010", gender = "other", type = "student", grade = 15, university = "bakinam", College = "habdasa"))
             try{
                 val responce = repository.Login(LoginAccountModel(email, password))
                 if (responce.isSuccessful) { //loggedin
                     "the responce is: " + responce.code() + " " + responce.body()!!
                     Log.v("responce", "the responce is: " + responce.body()!!)
                     repository.addUserData(responce.body()!!.asNetworkModel())
                     val data = repository.localDataSource.userDataBase.UserDao.get()
                     Log.v("responce", "the database data is: $data")

                 } else {
                     Log.v(
                         "responce",
                         "the responce is: " + responce.code() + " " + responce.errorBody()!!.string()
                     )
                 }
             } catch (throable: Throwable){
                 Log.v(
                     "responce",
                     "the responce is: " + throable.message
                 )
             }
         }

            Log.v("login","successful login")

        }
//            _showWrongAccountCredentialsDialog.value = true
    }

    fun navigationAfterSuccessfulLoginDone(){
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

}