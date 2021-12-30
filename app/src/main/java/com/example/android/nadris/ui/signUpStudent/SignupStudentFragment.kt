package com.example.android.nadris.ui.signUpStudent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.PasswordError
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupStudentFragmentBinding
import javax.inject.Inject

class signupStudentFragment : Fragment() {


    @Inject lateinit var viewModel: SignupStudentViewModel
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
        (requireContext().applicationContext as NadrisApplication).appGraph.injectFieldsOfSignupStudentViewModel(this)

        initiate()

        binding.studentViewModel = viewModel

        setAdapterForSpiners()

        registerObservers()

        return binding.root


    }

    private fun setAdapterForSpiners()
    {
        (binding.spGenderStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (binding.spTermStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!

    }
    private fun initiate() {
        gender = resources.getStringArray(R.array.GenderList)
        grade = resources.getStringArray(R.array.GradeList)
        adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, gender)
        adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, grade)

    }

    fun registerObservers(){
        this.viewModel.firstnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.firstNameTextFiled.error = "مطلوب"
            else
                binding.firstNameTextFiled.error = null

        }
        this.viewModel.lastnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.lastNameTextFiled.error = "مطلوب"
            else
                binding.lastNameTextFiled.error = null
        }
        this.viewModel.emailHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.emailTextFiled.error = "Invalid Email Address"
            else
                binding.emailTextFiled.error = null
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

            binding.passwordEditText1.error = errorMessage
        }
        viewModel.passwordNotMatch.observe(viewLifecycleOwner) {
            if (it)
                binding.passwordEditText2.error = "Password does not match"
            else
                binding.passwordEditText2.error = null
        }
        viewModel.phoneHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.phoneTextField.error = "not valid mobile number"
            else
                binding.phoneTextField.error = null
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
                //todo: navigate to home screen
                Toast.makeText(
                    context,
                    "navigated to home screen after successful login",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}