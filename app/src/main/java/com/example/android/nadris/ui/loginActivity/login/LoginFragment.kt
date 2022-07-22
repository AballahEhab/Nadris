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
import com.example.android.nadris.util.isVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    val TAG = "LoginFragment"

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

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {
                    binding.progressIndicatorLayer.isVisible(false)
                    enableUserInterAction(activity)
                    binding.loginErrorMessage.isVisible(false)
                },
                onLoading = {
                    binding.progressIndicatorLayer.isVisible(true)
                    disableUserInterAction(activity)
                    hideKeyboard(this.requireActivity())
                },
                onError = {
                    binding.loginErrorMessage.isVisible(true)
                    binding.loginErrorMessage.text = result.error
                },
                onSuccess = {
                    NadrisApplication.currentDatabaseUser = result.data
                     viewModel.getFirebaseUserData()
                    navigateToHomeFragment()
                    // show data
                }
            )

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

        return binding.root

    }

    private fun navigateToHomeFragment() {
        lateinit var directionClass: Class<*>
        if (NadrisApplication.currentDatabaseUser?.IsATeacher == true)
            directionClass = TeacherMainActivity::class.java
        else if (NadrisApplication.currentDatabaseUser?.IsATeacher == false)
            directionClass = StudentMainActivity::class.java

        startActivity(Intent(requireContext(), directionClass))
    }

    private fun navigateToSignUpFragment() {
        this.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    private fun hideKeyboard(activity: Activity) {
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