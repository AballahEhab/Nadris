package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class signupTeacherFragment : Fragment() {

    companion object {
        fun newInstance() = signupTeacherFragment()
    }

    private lateinit var viewModel: SignupTeacherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupTeacherViewModel::class.java)
        return inflater.inflate(R.layout.signup_teacher_fragment, container, false)
    }



}