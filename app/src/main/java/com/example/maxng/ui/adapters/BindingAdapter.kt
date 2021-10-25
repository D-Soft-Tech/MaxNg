package com.example.maxng.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.maxng.constants.AppConstants
import com.example.maxng.models.mapper.Domain

@BindingAdapter("loadRandomImage")
fun loadRandomImage(imageView: ImageView, data: Domain) {
    imageView.load(data.image)
}

@BindingAdapter("showFavouriteDrawable")
fun loadFavDrawable(imageView: ImageView, currentData: Domain) {
    if (currentData.liked) {
        imageView.setImageResource(AppConstants.hearts[1])
    } else {
        imageView.setImageResource(AppConstants.hearts[0])
    }
}

@BindingAdapter("changeDrawableAtOnClick")
fun changeDrawableAtOnClick(imageView: ImageView, currentData: Domain) {
    if (currentData.liked) {
        imageView.setImageResource(AppConstants.hearts[1])
    } else {
        imageView.setImageResource(AppConstants.hearts[0])
    }
}
