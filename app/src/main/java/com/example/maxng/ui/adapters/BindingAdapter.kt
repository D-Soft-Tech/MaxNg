package com.example.maxng.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.maxng.constants.AppConstants.IMAGES
import com.example.maxng.constants.AppConstants.randomNum

@BindingAdapter("loadRandomImage")
fun loadRandomImage(imageView: ImageView, holder: String) {
    imageView.setImageResource(IMAGES[randomNum])
}
