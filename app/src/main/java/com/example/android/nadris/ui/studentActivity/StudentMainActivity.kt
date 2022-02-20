package com.example.android.nadris.ui.studentActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ActivityStudnetMainAcitivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudnetMainAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudnetMainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_studnet_main_acitivity)

        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}