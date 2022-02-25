package com.example.android.nadris.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.ui.loginActivity.MainActivity
import com.example.android.nadris.util.login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val flow = NadrisApplication.instance?.repo?.getUser()

        CoroutineScope(Dispatchers.IO).launch{
            flow?.collect { userdata ->
                userdata?.let {
                    NadrisApplication.userData = it
                    login(this@SplashActivity,baseContext)
                    return@collect
                }
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            }

        }

    }


}