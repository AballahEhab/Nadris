package com.example.android.nadris.ui.signUpTeacher

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.android.nadris.PasswordError
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupTeacherFragmentBinding

class signupTeacherFragment : Fragment() {

//    companion object {
//        fun newInstance() = signupTeacherFragment()
//    }

    private lateinit var viewModel: SignupTeacherViewModel
    private lateinit var binding : SignupTeacherFragmentBinding
    private lateinit var gender : Array<String>
    private lateinit var collage: Array<String>
    private lateinit var universty: Array<String>
    private lateinit var subjects: Array<String>
    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter2 :ArrayAdapter<String>
    private lateinit var adapter3 :ArrayAdapter<String>
    private lateinit var adapter4 :ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        inflater.inflate(R.layout.signup_teacher_fragment, container, false)
        binding = SignupTeacherFragmentBinding.inflate(inflater)

        initiate()
        setAdaptersForSpinners()


        binding.teacherViewModel = viewModel

        registerObservers()

//        bindigin.firstNameTextFiled.setOnFocusChangeListener{_,focused ->
//            if(!focused){
//                bindigin.edtFirstNameTeacher.helperText= viewModel.validFirstName()
//            }
//
//        }
//        bindigin.lastNameTextFiled.setOnFocusChangeListener{_,focused ->
//            if(!focused){
//                bindigin.edtLastNameTeacher.helperText= viewModel.validLastName()
//            }
//        }
//
//
//        bindigin.emailTextFiled.setOnFocusChangeListener{_,focused ->
//            if(!focused){
//                bindigin.edtEmailTeacherSignup.helperText= viewModel.validEmail()
//            }
//        }
//        bindigin.passwordEditText1.setOnFocusChangeListener{_ ,focused ->
//            if(!focused){
//                bindigin.edtPassword1TeacherSignup.helperText= viewModel.validPassword1()
//            }
//        }
//
//        bindigin.passwordEditText2.setOnFocusChangeListener{_ ,focused ->
//            if(!focused){
//                bindigin.edtPassword2TeacherSignup.helperText= viewModel.validPassword2()
//            }
//        }
//
//        bindigin.phoneTextField.setOnFocusChangeListener{_ ,focused ->
//            if(!focused){
//                bindigin.edtPhoneTeacherSingup.helperText= viewModel.validPhone()
//            }
//        }
        return binding.root
    }
    fun setAdaptersForSpinners(){
        (binding.spGranderTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (binding.collageTeacherSingup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!
        (binding.spUnvistyTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter3)!!
        (binding.spSubjectTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter4)!!
    }

    fun initiate(){
        viewModel = ViewModelProvider(this).get(SignupTeacherViewModel::class.java)
        gender = resources.getStringArray(R.array.GenderList)
         collage = resources.getStringArray(R.array.collage)
         universty = resources.getStringArray(R.array.universty)
         subjects = resources.getStringArray(R.array.subject)
         adapter1= ArrayAdapter(requireContext(), R.layout.list_item, gender)
         adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, collage)
         adapter3 = ArrayAdapter(requireContext(), R.layout.list_item, universty)
         adapter4 = ArrayAdapter(requireContext(), R.layout.list_item, subjects)
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
                binding.spGranderTeacherSignup.error = "please set the gender"
            else
                binding.spGranderTeacherSignup.error = null

        }
        viewModel.subjectsHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spSubjectTeacherSignup.error = "please set the grade"
            else
                binding.spSubjectTeacherSignup.error = null
        }
        viewModel.collageHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.collageTeacherSingup.error = "please set the college"
            else
                binding.collageTeacherSingup.error = null
        }
        viewModel.universtyHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spUnvistyTeacherSignup.error = "please set the univercity"
            else
                binding.spUnvistyTeacherSignup.error = null
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