package com.example.android.nadris

import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.example.android.nadris.login.LoginViewModel

@BindingAdapter("onEmailChangedSetTo")
fun EditText.listenToEmailValue(viewModel:LoginViewModel){
    addTextChangedListener (
        onTextChanged = { text, start, before, count ->
            viewModel.emailOrMobile = text.toString()
        })

}
@BindingAdapter("onPasswordchangedSetTo")
fun EditText.listenToPasswordValue(viewModel:LoginViewModel){
    addTextChangedListener (
        onTextChanged = { text, start, before, count ->
            viewModel.password = text.toString()
        })

}