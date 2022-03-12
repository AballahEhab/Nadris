package com.example.android.nadris.ui.loginActivity.signUpStudent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentSignupStudentBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.util.getErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class signupStudentFragment : Fragment() {

    val viewModel: SignupStudentViewModel by viewModels()
    private lateinit var binding: FragmentSignupStudentBinding
    private lateinit var grade: Array<String>
    private lateinit var adapter1: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        inflater.inflate(R.layout.fragment_signup_student, container, false)
        binding = FragmentSignupStudentBinding.inflate(inflater)
        initiate()
        binding.studentViewModel = viewModel
        viewModel.getSections()
        binding.lifecycleOwner = this
        setAdapterForSpinners()
        registerObservers()

        return binding.root
    }

    private fun setAdapterForSpinners() {
        (binding.spGenderStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
    }

    private fun initiate() {
        viewModel.genderList.addAll(resources.getStringArray(R.array.GenderList))
        grade = resources.getStringArray(R.array.GradeList)
        adapter1 = ArrayAdapter(requireContext(), R.layout.item_gender_list, viewModel.genderList)
        viewModel.sections.observe(viewLifecycleOwner){list->
          val adapter = ArrayAdapter(requireContext(), R.layout.item_gender_list,list.map { it.name } )
            (binding.spTermStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        }
    }

    fun registerObservers() {
        this.viewModel.firstnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtFirstNameStudent.error = "مطلوب"
            else
                binding.edtFirstNameStudent.error = null

        }
        this.viewModel.lastnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtLastNameStudent. error = "مطلوب"
            else
                binding.edtLastNameStudent.error = null
        }
        this.viewModel.emailHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtEmailStudentSignup.error = "Invalid Email Address"
            else
                binding.edtEmailStudentSignup.error = null
        }
        this.viewModel.password1HaveError.observe(viewLifecycleOwner) {
            var errorMessage: String? = null
            errorMessage= getErrorMessage(viewModel.passwordErrorType)
            binding.edtPassword1StudentSignup.error = errorMessage
        }
        viewModel.passwordNotMatch.observe(viewLifecycleOwner) {
            if (it)
                binding.edtPassword2StudentSignup.error = "Password does not match"
            else
                binding.edtPassword2StudentSignup.error = null
        }
        viewModel.phoneHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtPhoneStudentSingup.error = "not valid mobile number"
            else
                binding.edtPhoneStudentSingup.error = null
        }
        viewModel.ganderHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spGenderStudentSignup.error = "please set the gender"
            else
                binding.spGenderStudentSignup.error = null

        }
        viewModel.gradeHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spTermStudentSignup.error = "please set the grade"
            else
                binding.spTermStudentSignup.error = null
        }

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireContext(), StudentMainActivity::class.java))
                this.activity?.finish()
            }
        }
    }
}