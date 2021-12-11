package com.example.android.nadris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.nadris.databinding.SignupStudentFragmentBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding :SignupStudentFragmentBinding = DataBindingUtil.setContentView(this,R.id.signupStudentFragment)

        val gender = resources.getStringArray(R.array.GenderList)
        val adapter = ArrayAdapter(this, R.layout.list_item, gender)
        val genderTextFeild=findViewById<TextInputLayout>(R.id.sp_gender_student_signup)
        (genderTextFeild.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val viewModel = ViewModelProvider(this).get(SignupStudentViewModel::class.java)
        //confirm button


        findViewById<Button>(R.id.containedButton).setOnClickListener {
            Toast.makeText(this,viewModel.email, Toast.LENGTH_LONG).show()
            Log.v("email: ",viewModel.email)

        }




    }
}