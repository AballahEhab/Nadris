package com.example.android.nadris.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(visible:Boolean){
    if (visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}