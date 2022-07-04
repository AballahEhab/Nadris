package com.example.android.nadris.ui.testactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nadris.R
import com.example.android.nadris.network.firebase.services.AuthService
import com.example.android.nadris.network.firebase.services.UserService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    @Inject lateinit var auth: AuthService
    @Inject lateinit var userSer:UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val email = "abdo@test.com"
        val password = "Test!123"

        Log.v("firebasetest0","create account called")

//        userSer.createNewUser(User("jjjjj","abdo","hamada",email,false,"third year high school",listOf(),"01011123456",false,"N/A","N/A","")
//        ).addOnCompleteListener {
//            Log.v("anything", it.isSuccessful.toString())
//            Log.v("anything", it.exception?.message.toString())
//
//        }

        //chaining two tasks
//        auth.createUserWithEmailAndPassword(email, password)
//            .continueWithTask(object:Continuation<AuthResult,Task<Void>>{
//                override fun then(p0: Task<AuthResult>): Task<Void> {
//
//                    val user = User(auth.currentUser()!!.uid,"abdo","hamada",email,false,"third year high school",listOf(),"01011123456",false,"N/A","N/A","")
//                return userSer.createNewUser(user)
//                }
//
//            }).addOnCompleteListener {
//                Log.v("anything", it.isSuccessful.toString())
//        Log.v("anything", it.exception?.message.toString())
//
//            }

    }
}