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
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentLoginBinding
import com.example.android.nadris.ui.studentActivity.StudentMainActivity
import com.example.android.nadris.ui.teacherActivity.TeacherMainActivity
import com.example.android.nadris.util.disableUserInterAction
import com.example.android.nadris.util.enableUserInterAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var  binding: FragmentLoginBinding

     val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

////inside Fragment
//        val job = Job()
//        val uiScope = CoroutineScope(Dispatchers.Main + job)

//        runBlocking{
//            viewModel.getUser() }




        inflater.inflate(R.layout.fragment_login, container, false)

        binding = FragmentLoginBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner

        //using view model to save UI state
        binding.viewModel = viewModel

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {

                login()
            }
        }

        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner) {
            if (it) {
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

    override fun onStart() {
        super.onStart()
        NadrisApplication.userData?.let {
            login()
        }
    }

    private fun login() {
        lateinit var directionClass:Class<*>
        if (NadrisApplication.userData?.Type == "student")
            directionClass = StudentMainActivity::class.java
        else if(NadrisApplication.userData?.Type == "teacher")
            directionClass = TeacherMainActivity::class.java

        startActivity(Intent(requireContext(), directionClass))
        this.activity?.finish()
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