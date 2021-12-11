package com.example.android.nadris

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter

@BindingAdapter("onEmailChangedSetTo")
fun EditText.listenToEmailValue(viewModel:SignupStudentViewModel){
    addTextChangedListener (
        onTextChanged = { text, start, before, count ->
            viewModel.email = text.toString()
        }) }

