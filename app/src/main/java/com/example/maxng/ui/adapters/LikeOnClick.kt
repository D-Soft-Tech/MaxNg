package com.example.maxng.ui.adapters

import android.widget.ImageView
import com.example.maxng.models.mapper.Domain

interface LikeOnClick {
    fun favourite(view: ImageView, data: Domain)
}