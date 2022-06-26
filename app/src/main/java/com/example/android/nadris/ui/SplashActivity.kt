package com.example.android.nadris.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.ui.loginActivity.MainActivity
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val firebaseUser = NadrisApplication.instance?.repo?.getCurrentFirebaseUser()

        if (firebaseUser == null)
            navigateToLoginActivity()

        else {
            CoroutineScope(Dispatchers.Main).launch {
               getCurrentUserLocalData()
                navigateToHomeActivity()
            }
        }
    }

    private suspend fun getCurrentUserLocalData() {
        NadrisApplication.currentUserLocalData =
            NadrisApplication.instance?.repo?.getLocalUserData()
    }

    private fun navigateToHomeActivity() {

        when (NadrisApplication.currentUserLocalData?.Type) {
            true -> navigateToTeacherMainActivity()
            false -> navigateToStudentHomeActivity()
            else -> navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToTeacherMainActivity() {
        startActivity(Intent(this, TeacherMainActivity::class.java))
    }

    private fun navigateToStudentHomeActivity() {
        startActivity(Intent(this, StudentMainActivity::class.java))
    }


}