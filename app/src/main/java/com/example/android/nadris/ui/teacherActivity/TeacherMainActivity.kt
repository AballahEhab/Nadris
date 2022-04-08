package com.example.android.nadris.ui.teacherActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.nadris.NadrisApplication.Companion.userData
import com.example.android.nadris.R
import com.example.android.nadris.databinding.ActivityTeacherMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeacherMainActivity : AppCompatActivity() {

     lateinit var binding: ActivityTeacherMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
//        Log.i("type", userData!!.isTeacher().toString())
        val navController = findNavController(R.id.nav_host_fragment_activity_teacher_main)
        visibilityNavElements(navController)
        navView.setupWithNavController(navController)
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.frafragTextEditor,R.id.adding_sections_fragment->binding.navView.visibility = View.GONE
                else -> binding.navView.visibility = View.VISIBLE
            }
        }
    }
}