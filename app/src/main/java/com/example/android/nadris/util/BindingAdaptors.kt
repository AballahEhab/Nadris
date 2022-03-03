package com.example.android.nadris.util

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
fun TextView.textWithFormat(stringRecourse:Int, /*vararg*/ formatterInput:Long){
    this.text = String.format(context.getString(stringRecourse),formatterInput)
}

@BindingAdapter("checked_image", "unchecked_image", "state")
fun ImageView.setImageResourceAccordingToCheckState(checked_image:Int, unchecked_image:Int,state:Boolean){
    if (state) {
        this.setImageResource(checked_image)
    } else {
        this.setImageResource(unchecked_image)
    }
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