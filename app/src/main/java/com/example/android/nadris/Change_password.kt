package com.example.android.nadris

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.databinding.FragmentChangePasswordBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.util.getErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Change_password : Fragment() {

    val viewModel: ChangePasswordViewModel by viewModels()
    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.fragment_change_password, container, false)
        binding = FragmentChangePasswordBinding.inflate(inflater)
        binding.viewModel = viewModel

        OldPasswordObservers()
        registerObservers()

        binding.btnClose.setOnClickListener {
            this.findNavController()
                .navigate(Change_passwordDirections.actionChangePasswordToSettingsFragment())
        }

        binding.btnSave.setOnClickListener {
          //viewModel.onSaveClicked()


        }

        viewModel.changePasswordResult.observe(viewLifecycleOwner){
            it.handleRepoResponse(
                onPreExecute = {
                    Toast.makeText(requireContext(),"جاري العمل علي تغيرر كلمه المرور ",Toast.LENGTH_SHORT).show()

                },
                onLoading = {

                },
                onError = {

                    Toast.makeText(requireContext(),"يرجي التأكد من كلمه المرور وإعاده المحاوله ",Toast.LENGTH_SHORT).show()
                },
                onSuccess = {
                    Toast.makeText(requireContext(),"تم تغيير الرقم كلمه المرور  ",Toast.LENGTH_SHORT).show()


                }
            )
        }

        return binding.root
    }

    //pass error in old password to helperText
    private fun OldPasswordObservers() {
        viewModel.passwordErrorMessage.observe(viewLifecycleOwner) {
            binding.edtOldPassword.error = it
        }

    }

    //pass error in new password to helperText
    private fun registerObservers() {
        this.viewModel.password1HaveError.observe(viewLifecycleOwner) {
            var errorMessage: String? = null
            errorMessage = getErrorMessage(viewModel.passwordErrorType)
            binding.edtNewPassword1.error = errorMessage
        }
        viewModel.passwordNotMatch.observe(viewLifecycleOwner) {
            if (it)
                binding.edtRetypeNewPassowrd2.error = "Password does not match"
            else
                binding.edtRetypeNewPassowrd2.error = null
        }

    }





}