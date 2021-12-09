package com.example.android.nadris.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.R
import com.example.android.nadris.databinding.LoginFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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


        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner,{
            if (it){
                //todo: navigate to home screen
                Toast.makeText(context,"navigated to home screen after successful login",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
                viewModel.navigationToCreateAccountDone()
            }
        })

        viewModel.showWrongAccountCredentialsDialog.observe(viewLifecycleOwner,{
            if (it){
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setCancelable(false)
                    .setTitle(getString(R.string.wron_cridentials))

                    .setPositiveButton("ok") { dialog, which ->
                        dialog.cancel()
                    }.show()

                val button = dialog.findViewById<Button>(android.R.id.button1)
                val layoutParams:LinearLayout.LayoutParams = button!!.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                button.setLayoutParams(layoutParams)
            }
        })






        return binding.root

    }


}