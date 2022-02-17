package com.example.android.nadris.ui.loginActivity.signUpStudent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.android.nadris.PasswordError
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupStudentFragmentBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class signupStudentFragment : Fragment() {

    val viewModel: SignupStudentViewModel by viewModels()
    private lateinit var binding :SignupStudentFragmentBinding
    private lateinit var gender : Array<String>
    private lateinit var grade:Array<String>
    private lateinit var adapter1:ArrayAdapter<String>
    private lateinit var adapter2 :ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.signup_student_fragment, container, false)
        binding = SignupStudentFragmentBinding.inflate(inflater)


        initiate()

        binding.studentViewModel = viewModel

        binding.lifecycleOwner = this

        setAdapterForSpinners()

        registerObservers()

        viewModel.grade.observe(this.viewLifecycleOwner){
            viewModel.gradeId = grade.indexOf(it)
        }
        viewModel.gender.observe(this.viewLifecycleOwner){
            viewModel.genderId = gender.indexOf(it)
        }

        return binding.root


    }

    private fun setAdapterForSpinners()
    {
        (binding.spGenderStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (binding.spTermStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!

    }
    private fun initiate() {
        gender = this.resources.getStringArray(R.array.GenderList)
        grade = resources.getStringArray(R.array.GradeList)
        adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, gender)
        adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, grade)

    }

    fun registerObservers(){
        this.viewModel.firstnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtFirstNameStudent.error = "مطلوب"
            else
                binding.edtFirstNameStudent.error = null

        }
        this.viewModel.lastnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtLastNameStudent.error = "مطلوب"
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
            var errorMessage :String? = null
            if (it)
                when(viewModel.passwordErrorType){
                    PasswordError.SHORT_PASSWORD->
                        errorMessage = "Minimum 8 Character password"
                    PasswordError.NOT_CONTAIN_UPPERCASE->
                        errorMessage = "Must Contain 1 Upper-case Characters"
                    PasswordError.NOT_CONTAIN_LOWER_CASE->
                        errorMessage = "Must Contain 1 Lower-case Characters"
                    PasswordError.NOT_CONTAIN_SPECIAL_CHARACTER->
                        errorMessage = "Must Contain 1 Special Characters(@#\$%^&+=)"
                    null -> errorMessage = null // impossible case
                }

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