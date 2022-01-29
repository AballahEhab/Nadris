package com.example.android.nadris.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.LoginFragmentBinding
import com.example.android.nadris.ui.signUp.SignupFragment
import javax.inject.Inject

class LoginFragment : Fragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }
    lateinit var  binding: LoginFragmentBinding

    @Inject lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        inflater.inflate(R.layout.login_fragment, container, false)

        ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = LoginFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this


        (requireContext().applicationContext as NadrisApplication).appGraph.injectFieldsOfLoginFragment(this)

        //using view model to save UI state
        binding.viewModel = viewModel


        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner,{
            if (it){
                //todo: navigate to home screen after creating home screen activity
                Toast.makeText(context,"navigated to home screen after successful login",Toast.LENGTH_SHORT).show()
                viewModel.navigationAfterSuccessfulLoginDone()
            }
        })

        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner, {
            if(it){
                //todo
                this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
                viewModel.navigationToCreateAccountDone()
            }
        })

        viewModel.emailHaveError.observe(this.viewLifecycleOwner,{
            if (it)
                binding.emailEdTxt.error = "this and error"
            else
                binding.emailEdTxt.error = null


        })

        viewModel.passwordHaveError.observe(this.viewLifecycleOwner,{
            if(it)
                binding.edtPasswordLogin.error = "password has error"
            else
                binding.edtPasswordLogin.error = null

        })






        return binding.root

    }


}