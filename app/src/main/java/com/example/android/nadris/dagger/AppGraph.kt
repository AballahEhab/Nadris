package com.example.android.nadris.dagger

import com.example.android.nadris.repository.LocalDataSource
import com.example.android.nadris.repository.Repository
import com.example.android.nadris.daggerModules.DataBaseModule
import com.example.android.nadris.daggerModules.NetworkAPIServiceModule
import com.example.android.nadris.database.UserDataBase
import com.example.android.nadris.network.NadrisAPIService
import com.example.android.nadris.repository.RemoteDataSource
import com.example.android.nadris.ui.login.LoginFragment
import com.example.android.nadris.ui.login.LoginViewModel
import com.example.android.nadris.ui.signUp.SignupFragment
import com.example.android.nadris.ui.signUp.SignupViewModel
import com.example.android.nadris.ui.signUpStudent.SignupStudentViewModel
import com.example.android.nadris.ui.signUpStudent.signupStudentFragment
import com.example.android.nadris.ui.signUpTeacher.SignupTeacherViewModel
import com.example.android.nadris.ui.signUpTeacher.signupTeacherFragment
import dagger.Component

@Component(modules = [DataBaseModule::class,NetworkAPIServiceModule::class])
interface AppGraph {

    fun getDataBase():UserDataBase
    fun getLocalDataSource():LocalDataSource

    fun getNetWorkAPI():NadrisAPIService
    fun getRemoteDataSource():RemoteDataSource
    
    fun getRepository():Repository


    fun getLoginViewModel(): LoginViewModel
    fun injectFieldsOfLoginFragment(loginFragment: LoginFragment)

    fun getSignupStudentViewModel():SignupStudentViewModel
    fun injectFieldsOfSignupStudentViewModel(signupStudentFragment: signupStudentFragment)

    fun getSignUpViewModel():SignupViewModel
    fun injectFieldsOfSignupFragment(signupFragment: SignupFragment)

    fun getSignupTeacherViewModel():SignupTeacherViewModel
    fun injectFieldsOfSignupTeacherFragment(signupTeacherFragment: signupTeacherFragment)

}