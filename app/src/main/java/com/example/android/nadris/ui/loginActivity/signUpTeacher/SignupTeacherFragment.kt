package com.example.android.nadris.ui.loginActivity.signUpTeacher

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
import com.example.android.nadris.databinding.FragmentSignupTeacherBinding
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import com.example.android.nadris.util.getErrorMessage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class signupTeacherFragment : Fragment() {

    val viewModel: SignupTeacherViewModel by viewModels()
    private lateinit var binding: FragmentSignupTeacherBinding
    private lateinit var subjects: Array<String>
    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter4: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        inflater.inflate(R.layout.fragment_signup_teacher, container, false)

        binding = FragmentSignupTeacherBinding.inflate(inflater)

        initiate()
        viewModel.getUniversities()

        setAdaptersForSpinners()

        binding.teacherViewModel = viewModel

        binding.lifecycleOwner = this

        registerObservers()

        return binding.root
    }

    private fun setAdaptersForSpinners() {
        (binding.spGranderTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (binding.spSubjectTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter4)!!
    }

    private fun initiate() {
        viewModel.genderList.addAll(resources.getStringArray(R.array.GenderList))
        adapter1 = ArrayAdapter(requireContext(), R.layout.item_gender_list, viewModel.genderList)
        viewModel.universities.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(),
                R.layout.item_gender_list, list.map { it.name_ar })
            (binding.spUnvistyTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        }
        viewModel.colleges.observe(viewLifecycleOwner) { list ->
            val adapter = ArrayAdapter(requireContext(),
                R.layout.item_gender_list, list.map { it.name_ar })
            (binding.collageTeacherSingup.editText as? AutoCompleteTextView)?.setAdapter(adapter)!!
        }
        subjects = resources.getStringArray(R.array.subject)
        adapter4 = ArrayAdapter(requireContext(), R.layout.item_gender_list, subjects)
    }

    private fun registerObservers() {
        this.viewModel.firstnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtFirstNameTeacher.error = "مطلوب"
            else
                binding.edtFirstNameTeacher.error = null

        }
        this.viewModel.lastnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtLastNameTeacher.error = "مطلوب"
            else
                binding.edtLastNameTeacher.error = null
        }
        this.viewModel.emailHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtEmailTeacherSignup.error = "Invalid Email Address"
            else
                binding.edtEmailTeacherSignup.error = null
        }
        this.viewModel.password1HaveError.observe(viewLifecycleOwner) {
            var errorMessage: String? = null
            errorMessage= getErrorMessage(viewModel.passwordErrorType)
            binding.edtPassword1TeacherSignup.error = errorMessage
        }
        viewModel.passwordNotMatch.observe(viewLifecycleOwner) {
            if (it)
                binding.edtPassword2TeacherSignup.error = "Password does not match"
            else
                binding.edtPassword2TeacherSignup.error = null
        }
        viewModel.phoneHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.edtPhoneTeacherSingup.error = "not valid mobile number"
            else
                binding.edtPhoneTeacherSingup.error = null
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
//        viewModel.collageHaveError.observe(viewLifecycleOwner) {
//            if (it)
//                binding.collageTeacherSingup.error = "please set the college"
//            else
//                binding.collageTeacherSingup.error = null
//        }
        viewModel.universityHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spUnvistyTeacherSignup.error = "please set the university"
            else
                binding.spUnvistyTeacherSignup.error = null
        }


        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireContext(), TeacherMainActivity::class.java))
                this.activity?.finish()
            }
        }
    }
}