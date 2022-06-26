package com.example.android.nadris.ui.loginActivity.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.databinding.FragmentLoginBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import com.example.android.nadris.util.disableUserInterAction
import com.example.android.nadris.util.enableUserInterAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner

        //using view model to save UI state
        binding.viewModel = viewModel

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {
                navigateToHomeFragment()
                viewModel.navigateToHomeActivityDone()
            }
        }

        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner) {
            if (it) {
                navigateToSignUpFragment()
                viewModel.navigationToCreateAccountDone()
            }
        }

        viewModel.emailErrorMessage.observe(viewLifecycleOwner) {
            binding.materialEmailOrPhone.error = it
        }

        viewModel.passwordErrorMessage.observe(viewLifecycleOwner) {
            binding.edtPasswordLogin.error = it
        }

        viewModel.showIndicator.observe(this.viewLifecycleOwner) {
            if (it) {
                disableUserInterAction(activity)
                hideKeyboard(this.requireActivity())
            } else
                enableUserInterAction(activity)
        }

        return binding.root

    }

    override fun onStart() {
        super.onStart()
//        NadrisApplication.currentUserLocalData?.let {
//            navigateToHomeFragment()
//        }
    }

    private fun navigateToHomeFragment() {
        lateinit var directionClass: Class<*>
        if (NadrisApplication.currentUserLocalData?.Type == true)
            directionClass = StudentMainActivity::class.java
        else if (NadrisApplication.currentUserLocalData?.Type == false)
            directionClass = TeacherMainActivity::class.java

        startActivity(Intent(requireContext(), directionClass))
    }

    private fun navigateToSignUpFragment() {
        this.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
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