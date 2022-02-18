package com.example.android.nadris.ui.loginActivity.login

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.LoginFragmentBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import com.example.android.nadris.util.disableUserInterAction
import com.example.android.nadris.util.enableUserInterAction
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var  binding: LoginFragmentBinding

     val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        inflater.inflate(R.layout.login_fragment, container, false)

        binding = LoginFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner

        //using view model to save UI state
        binding.viewModel = viewModel


        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {

                lateinit var directionClass:Class<*>
                if (NadrisApplication.userData?.Type == "student")
                    directionClass = StudentMainActivity::class.java
                else if(NadrisApplication.userData?.Type == "teacher")
                    directionClass = TeacherMainActivity::class.java

                startActivity(Intent(requireContext(), directionClass))
                this.activity?.finish()
            }
        }

        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner) {
            if (it) {
                //todo
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
                viewModel.navigationToCreateAccountDone()
            }
        }

        viewModel.emailErrorMessage.observe(viewLifecycleOwner) {
            binding.materialEmailOrPhone.error = it
        }
        viewModel.passwordErrorMessage.observe(viewLifecycleOwner) {
            binding.edtPasswordLogin.error = it
        }


        viewModel.showIndicator.observe(this.viewLifecycleOwner){
            if (it) {
                disableUserInterAction(activity)
                hideKeyboard(this.requireActivity())
            }
            else
                enableUserInterAction(activity)
        }

        return binding.root

    }
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



}