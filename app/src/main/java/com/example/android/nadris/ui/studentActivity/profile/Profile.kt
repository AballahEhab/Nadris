package com.example.android.nadris.ui.studentActivity.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class profile : Fragment() {

    private lateinit var binding:FragmentProfileBinding
      val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_profile,container,false)
        binding= FragmentProfileBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.viewmodel=viewModel

        binding.lifecycleOwner = this
        viewModel.getProfileInfo_from_api()




        return binding.root;
    }



}