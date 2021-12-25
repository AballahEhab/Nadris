package com.example.android.nadris.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

//        viewModel.showWrongAccountCredentialsDialog.observe(viewLifecycleOwner,{
//            if (it){
//                val dialog = MaterialAlertDialogBuilder(requireContext())
//                    .setCancelable(false)
//                    .setTitle(getString(R.string.wron_cridentials))
//
//                    .setPositiveButton("ok") { dialog, which ->
//                        dialog.cancel()
//                    }.show()
//
//                val button = dialog.findViewById<Button>(android.R.id.button1)
//                val layoutParams:LinearLayout.LayoutParams = button!!.layoutParams as LinearLayout.LayoutParams
//                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//                button.setLayoutParams(layoutParams)
//            }
//        })


        viewModel.emailHaveError.observe(this.viewLifecycleOwner,{
            if (it)
                binding.emailEdittxt.error = "this and error"
            else
                binding.emailEdittxt.error = null


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