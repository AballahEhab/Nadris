package com.example.android.nadris.ui.loginActivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ActivityMainBinding
import com.example.android.nadris.util.isVisible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        binding.retryConnectivityCheckBtn.setOnClickListener{
            NadrisApplication.instance?.connectivityMonitor?.checkConnectivity()
        }

            NadrisApplication.instance?.connectivityMonitor?.isOnline?.observe(this) {
            binding.offlineIcon.isVisible(!it)
            binding.retryConnectivityCheckBtn.isVisible(!it)
                if (it)
                    supportFragmentManager.beginTransaction().show(navHostFragment!!).commit()
                else
                    supportFragmentManager.beginTransaction().hide(navHostFragment!!).commit()

        }
    }
}