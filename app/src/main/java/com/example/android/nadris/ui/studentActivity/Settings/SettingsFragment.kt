package com.example.android.nadris.ui.studentActivity.Settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSettingsBinding
import com.example.android.nadris.ui.studentActivity.profile.ProfileViewModel

class SettingsFragment : Fragment() {

    private lateinit var binding:FragmentSettingsBinding
    val viewModel: SettingsViewModel by viewModels()

//    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_settings, container, false)
        binding = FragmentSettingsBinding.inflate(inflater)

        binding.imagBackProfile.setOnClickListener {
            this.findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToProfile())
        }

        binding.tvEditProfile.setOnClickListener {

        }

        binding.tvChangePassword.setOnClickListener {

        }

        binding.tvChangeClass.setOnClickListener {

        }

        binding.tvNightMode.setOnClickListener {

        }

        binding.tvChangeLanguage.setOnClickListener {

        }

        binding.tvHelp.setOnClickListener {

        }

        binding.tvSignOut.setOnClickListener {

        }



        return binding.root
    }



}