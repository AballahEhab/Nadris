package com.example.android.nadris.ui.signUpStudent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.android.nadris.PasswordError
import com.example.android.nadris.R
import com.example.android.nadris.databinding.SignupStudentFragmentBinding

class signupStudentFragment : Fragment() {

//    companion object {
//        fun newInstance() = signupStudentFragment()
//    }

    private lateinit var viewModel: SignupStudentViewModel
    private lateinit var binding :SignupStudentFragmentBinding
    private lateinit var gender : Array<String>
    private lateinit var grade:Array<String>
    private lateinit var adapter1:ArrayAdapter<String>
    private lateinit var adapter2 :ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inflater.inflate(R.layout.signup_student_fragment, container, false)
        binding = SignupStudentFragmentBinding.inflate(inflater)

        initiate()

        binding.studentViewModel = viewModel

        setAdapterForSpiners()

        registerObservers()
//        validateInputONFocusChane()




//        bindigin.containedButton.setOnClickListener {
//            var str:String=""+viewModel.email.value+"\n"+viewModel.password1.value+"\n"+viewModel.grade.value+"\n"+viewModel.gander.value;
//            Toast.makeText(requireContext(),str,Toast.LENGTH_LONG).show()
//        }


//        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner, Observer {
//            if (it) {
//                this.findNavController().navigate(signupStudentFragment.Directions)
//                viewModel.navigationToCreateAccountDone()
//            }
//        }

//        viewModel.showWrongAccountCredentialsDialog.observe(viewLifecycleOwner) {
//            if (it) {
//                val dialog = MaterialAlertDialogBuilder(requireContext())
//                    .setCancelable(false)
//                    .setTitle(getString(R.string.wron_cridentials))
//
//                    .setPositiveButton("ok") { dialog, which ->
//                        dialog.cancel()
//                    }.show()
//
//                val button = dialog.findViewById<Button>(android.R.id.button1)
//                val layoutParams: LinearLayout.LayoutParams = button!!.layoutParams as LinearLayout.LayoutParams
//                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//                button.layoutParams = layoutParams
//            }
//        }


        return binding.root


    }

    private fun setAdapterForSpiners()
    {
        (binding.spGenderStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (binding.spTermStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!

    }
    private fun initiate() {
        viewModel = ViewModelProvider(this).get(SignupStudentViewModel::class.java)
        gender = resources.getStringArray(R.array.GenderList)
        grade = resources.getStringArray(R.array.GradeList)
        adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, gender)
        adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, grade)

    }

//    fun validateInputONFocusChane(){
//        binding.firstNameTextFiled.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.validFirstName()
//            }
//
//        }
//        binding.lastNameTextFiled.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.validLastName()
//            }
//        }
//
//
//        binding.emailTextFiled.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.validEmail()
//            }
//        }
//        binding.passwordEditText1.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.validPassword1()
//            }
//        }
//
//        binding.passwordEditText2.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.matchPassword1ToPassword2()
//            }
//        }
//
//        binding.phoneTextField.setOnFocusChangeListener { _, focused ->
//            if (!focused) {
//                viewModel.validPhone()
//            }
//        }
//    }

    fun registerObservers(){
        this.viewModel.firstnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.firstNameTextFiled.error = "مطلوب"
            else
                binding.firstNameTextFiled.error = null

        }
        this.viewModel.lastnameHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.lastNameTextFiled.error = "مطلوب"
            else
                binding.lastNameTextFiled.error = null
        }
        this.viewModel.emailHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.emailTextFiled.error = "Invalid Email Address"
            else
                binding.emailTextFiled.error = null
        }
        this.viewModel.password1HaveError.observe(viewLifecycleOwner) {
            var errorMessage :String? = null
            if (it)
                when(viewModel.passwordErrorType){
                    PasswordError.SHORT_PASSWORD->
                        errorMessage = "Minimum 8 Character password"
                    PasswordError.NOT_CONTAIN_UPPERCASE->
                        errorMessage = "Must Contain 1 Upper-case Characters"
                    PasswordError.NOT_CONTAIN_LOWER_CASE->
                        errorMessage = "Must Contain 1 Lower-case Characters"
                    PasswordError.NOT_CONTAIN_SPECIAL_CHARACTER->
                        errorMessage = "Must Contain 1 Special Characters(@#\$%^&+=)"
                    null -> errorMessage = null // impossible case
                }

            binding.passwordEditText1.error = errorMessage
        }
        viewModel.passwordNotMatch.observe(viewLifecycleOwner) {
            if (it)
                binding.passwordEditText2.error = "Password does not match"
            else
                binding.passwordEditText2.error = null
        }
        viewModel.phoneHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.phoneTextField.error = "not valid mobile number"
            else
                binding.phoneTextField.error = null
        }
        viewModel.ganderHaveError.observe(viewLifecycleOwner) {
            if (it)
            binding.spGenderStudentSignup.error = "please set the gender"
            else
            binding.spGenderStudentSignup.error = null

        }
        viewModel.gradeHaveError.observe(viewLifecycleOwner) {
            if (it)
                binding.spTermStudentSignup.error = "please set the grade"
            else
                binding.spTermStudentSignup.error = null
        }

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
            if (it) {
                //todo: navigate to home screen
                Toast.makeText(
                    context,
                    "navigated to home screen after successful login",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}