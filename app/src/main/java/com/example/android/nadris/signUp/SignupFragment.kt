package com.example.android.nadris.signUp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupFragmentBinding

class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
         inflater.inflate(R.layout.signup_fragment, container, false)
        val binding = SignupFragmentBinding.inflate(inflater)


        binding.viewModle = viewModel

        viewModel.navigate_to_teacher_signUp.observe(this.viewLifecycleOwner,{
            if (it){
                this.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToSignupTeacherFragment())
                viewModel.navigation_to_teacher_signUp_done()
            }
        })
        viewModel.navigate_to_student_signUp.observe(this.viewLifecycleOwner,{
            if (it){
                this.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToSignupStudentFragment())
                viewModel.navigation_to_student_signUp_done()
            }
        })
        viewModel.navigate_to_login.observe(this.viewLifecycleOwner,{
            if(it){
                this.findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
            }
        })


        return binding.root
    }
}