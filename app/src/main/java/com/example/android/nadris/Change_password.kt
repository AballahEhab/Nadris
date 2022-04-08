package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Change_password : Fragment() {

     val viewModel: ChangePasswordViewModel by viewModels ()
    private lateinit var binding : FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_change_password, container, false)
        binding = FragmentChangePasswordBinding.inflate(inflater)

        binding.btnClose.setOnClickListener {
            this.findNavController()
                .navigate(Change_passwordDirections.actionChangePasswordToSettingsFragment())
        }

        binding.btnSave.setOnClickListener {
            //check old password true and save new passowrd
//            if(binding.edtNewPassowrd.text.toString() == binding.edtRetypeNewPassowrd.text.toString()&&
//                binding.edtNewPassowrd.text.toString().isNotEmpty() &&
//                binding.edtOldPassword.text.toString().isNotEmpty()){
//                //send old password to api to cheeck on true or false
//
//            }else{
//                Toast.makeText(requireContext(),"الباسورد غير متساوي",Toast.LENGTH_SHORT).show()
//            }


        }


        return binding.root
    }



}