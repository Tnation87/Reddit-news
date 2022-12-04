package com.example.redditnews.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageUrl(
    imageUrl: String?,
) {
    val builder = Glide.with(this).load(imageUrl).apply(RequestOptions().centerInside())
    builder.into(this)
}