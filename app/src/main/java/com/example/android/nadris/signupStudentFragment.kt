package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.nadris.databinding.SignupStudentFragmentBinding

class signupStudentFragment : Fragment() {

    companion object {
        fun newInstance() = signupStudentFragment()
    }

    private lateinit var viewModel: SignupStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupStudentViewModel::class.java)


        inflater.inflate(R.layout.signup_student_fragment, container, false)
        val bindigin = SignupStudentFragmentBinding.inflate(inflater)
        val gender = resources.getStringArray(R.array.GenderList)
        val grade = resources.getStringArray(R.array.GradeList)
        val adapter1= ArrayAdapter(requireContext(), R.layout.list_item, gender)
        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, grade)
        //val genderTextFeild=container!!.findViewById<TextInputLayout>(R.id.sp_gender_student_signup)
        (bindigin.spGenderStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (bindigin.spTermStudentSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!



        bindigin.studentViewModel = viewModel

        bindigin.firstNameTextFiled.setOnFocusChangeListener{_,focused ->
                if(!focused){
                    bindigin.edtFirstNameStudent.helperText= viewModel.validFirstName()
                }

        }
        bindigin.lastNameTextFiled.setOnFocusChangeListener{_,focused ->
                if(!focused){
                    bindigin.edtLastNameStudent.helperText= viewModel.validLastName()
                }
            }


        bindigin.emailTextFiled.setOnFocusChangeListener{_,focused ->
                if(!focused){
                    bindigin.edtEmailStudentSignup.helperText= viewModel.validEmail()
                }
            }
        bindigin.passwordEditText1.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPassword1StudentSignup.helperText= viewModel.validPassword1()
            }
        }

        bindigin.passwordEditText2.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPassword2StudentSignup.helperText= viewModel.validPassword2()
            }
        }

        bindigin.phoneTextField.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPhoneStudentSingup.helperText= viewModel.validPhone()
            }
        }

//        bindigin.containedButton.setOnClickListener {
//            var str:String=""+viewModel.email.value+"\n"+viewModel.password1.value+"\n"+viewModel.grade.value+"\n"+viewModel.gander.value;
//            Toast.makeText(requireContext(),str,Toast.LENGTH_LONG).show()
//        }
//        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner) {
//            if (it) {
//                //todo: navigate to home screen
//                Toast.makeText(context, "navigated to home screen after successful login", Toast.LENGTH_LONG).show()
//            }
//        }

//        viewModel.navigateToCreateAccount.observe(viewLifecycleOwner, Observer {
//            if(it){
//               this.findNavController().navigate(signupStudentFragment.Directions)
//                viewModel.navigationToCreateAccountDone()
//            }


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


        return bindigin.root


    }



}