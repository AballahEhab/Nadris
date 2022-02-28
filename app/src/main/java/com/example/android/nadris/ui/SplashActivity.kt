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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        CoroutineScope(Dispatchers.Main).launch{
                NadrisApplication.userData = NadrisApplication.instance?.repo?.getUser()
            if(NadrisApplication.userData == null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                return@launch
            }
            login()
        }

    }
    fun login( ) {
        lateinit var directionClass:Class<*>
        if (NadrisApplication.userData?.Type == "student")
            directionClass = StudentMainActivity::class.java
        else if(NadrisApplication.userData?.Type == "teacher")
            directionClass = TeacherMainActivity::class.java

        startActivity(Intent(this, directionClass))
        this.finish()
    }


}