package com.example.android.nadris.ui.teacherActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ActivityTeacherMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_teacher_main)

        navView.setupWithNavController(navController)
    }
}