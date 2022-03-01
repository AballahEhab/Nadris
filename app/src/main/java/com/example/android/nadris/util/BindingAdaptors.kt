package com.example.android.nadris.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.nadris.R

@BindingAdapter("isVisible")
fun View.isVisible(visible:Boolean){
    if (visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

@BindingAdapter("stringRecourse", "formatterInput")
fun TextView.TextWithFormat(stringRecourse:Int, /*vararg*/ formatterInput:Int){
    this.text = String.format(context.getString(stringRecourse),formatterInput)
}
//@BindingAdapter("imageUrl", "error")
//fun loadImage(view: ImageView, url: String, error: Drawable) {
//    Picasso.get().load(url).error(error).into(view)
//}
//
//<ImageView app:imageUrl="@{venue.imageUrl}" app:error="@{@drawable/venueError}" />

//@BindingAdapter("image_resource")
//fun  ImageView.imageResource(imageRecourse:Int){
//    this.setImageResource(imageRecourse)
//}