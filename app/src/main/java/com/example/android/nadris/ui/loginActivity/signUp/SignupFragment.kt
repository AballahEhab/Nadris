package com.example.android.nadris.ui.loginActivity.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {


    val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

         inflater.inflate(R.layout.signup_fragment, container, false)

        val binding = SignupFragmentBinding.inflate(inflater)

        binding.viewModle = viewModel

        viewModel.navigate_to_teacher_signUp.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(SignupFragmentDirections.actionSignupFragmentToSignupTeacherFragment())
                viewModel.navigation_to_teacher_signUp_done()
            }
        }
        viewModel.navigate_to_student_signUp.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(SignupFragmentDirections.actionSignupFragmentToSignupStudentFragment())
                viewModel.navigation_to_student_signUp_done()
            }
        }
        viewModel.navigate_to_login.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
                viewModel.navigation_to_login_done()
            }
        }


        return binding.root
    }
}