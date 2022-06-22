package com.example.android.nadris.ui.studentActivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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
//        visibilityNavElements(navController)
        navView.setupWithNavController(navController)


    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.private_profile_fragment,R.id.student_subjects_tabs_fragment,R.id.posts_fragment->binding.navView.visibility = View.VISIBLE
                else -> binding.navView.visibility = View.GONE
            }
        }
    }

}