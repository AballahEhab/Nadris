package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class signupStudentFragment : Fragment() {

    companion object {
        fun newInstance() = signupStudentFragment()
    }

    private lateinit var viewModel: SignupStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupStudentViewModel::class.java)

        val gender = resources.getStringArray(R.array.GenderList)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, gender)
        val genderTextFeild=container!!.findViewById<TextInputLayout>(R.id.sp_gender_student_signup)
        (genderTextFeild.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        return inflater.inflate(R.layout.signup_student_fragment, container, false)

    }



}