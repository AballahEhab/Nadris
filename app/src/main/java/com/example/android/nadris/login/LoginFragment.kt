package com.example.android.nadris.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.android.nadris.R
import com.example.android.nadris.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    lateinit var  binding: LoginFragmentBinding

//    companion object {
//        fun newInstance() = LoginFragment()
//    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

         inflater.inflate(R.layout.login_fragment, container, false)

        binding = LoginFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        //todo: creat observer to navigate to home screen
        viewModel.navigateToHomeScreen

        //todo: creat observer to navigate to creat account screen
        viewModel.navigateToCreateAccount




        return binding.root

    }


}