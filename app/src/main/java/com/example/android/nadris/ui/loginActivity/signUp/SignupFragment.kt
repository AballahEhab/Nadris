package com.example.android.nadris.ui.loginActivity.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {


    val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

         inflater.inflate(R.layout.fragment_signup, container, false)

        val binding = FragmentSignupBinding.inflate(inflater)

        binding.viewModle = viewModel

        viewModel.navigateToTeacherSignUp.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(SignupFragmentDirections.actionSignupFragmentToSignupTeacherFragment())
                viewModel.navigationTeacherSignUpDone()
            }
        }
        viewModel.navigateToStudentSignUp.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(SignupFragmentDirections.actionSignupFragmentToSignupStudentFragment())
                viewModel.navigationToStudentSignupDone()
            }
        }
        viewModel.navigateToLogin.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
                viewModel.navigationToLoginDone()
            }
        }

        return binding.root
    }
}