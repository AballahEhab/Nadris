package com.example.android.nadris

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.android.nadris.databinding.SignupStudentFragmentBinding
import com.example.android.nadris.databinding.SignupTeacherFragmentBinding

class signupTeacherFragment : Fragment() {

    companion object {
        fun newInstance() = signupTeacherFragment()
    }

    private lateinit var viewModel: SignupTeacherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupTeacherViewModel::class.java)


        inflater.inflate(R.layout.signup_teacher_fragment, container, false)
        val bindigin = SignupTeacherFragmentBinding.inflate(inflater)
        val gender = resources.getStringArray(R.array.GenderList)
        val collage = resources.getStringArray(R.array.collage)
        val universty = resources.getStringArray(R.array.universty)
        val subjects = resources.getStringArray(R.array.subject)
        val adapter1= ArrayAdapter(requireContext(), R.layout.list_item, gender)
        val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, collage)
        val adapter3 = ArrayAdapter(requireContext(), R.layout.list_item, universty)
        val adapter4 = ArrayAdapter(requireContext(), R.layout.list_item, subjects)
        (bindigin.spGranderTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter1)!!
        (bindigin.collageTeacherSingup.editText as? AutoCompleteTextView)?.setAdapter(adapter2)!!
        (bindigin.spUnvistyTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter3)!!
        (bindigin.spSubjectTeacherSignup.editText as? AutoCompleteTextView)?.setAdapter(adapter4)!!


        bindigin.teacherViewModel = viewModel

        bindigin.firstNameTextFiled.setOnFocusChangeListener{_,focused ->
            if(!focused){
                bindigin.edtFirstNameTeacher.helperText= viewModel.validFirstName()
            }

        }
        bindigin.lastNameTextFiled.setOnFocusChangeListener{_,focused ->
            if(!focused){
                bindigin.edtLastNameTeacher.helperText= viewModel.validLastName()
            }
        }


        bindigin.emailTextFiled.setOnFocusChangeListener{_,focused ->
            if(!focused){
                bindigin.edtEmailTeacherSignup.helperText= viewModel.validEmail()
            }
        }
        bindigin.passwordEditText1.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPassword1TeacherSignup.helperText= viewModel.validPassword1()
            }
        }

        bindigin.passwordEditText2.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPassword2TeacherSignup.helperText= viewModel.validPassword2()
            }
        }

        bindigin.phoneTextField.setOnFocusChangeListener{_ ,focused ->
            if(!focused){
                bindigin.edtPhoneTeacherSingup.helperText= viewModel.validPhone()
            }
        }
        return bindigin.root
    }


}