package com.example.nux.news.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("visibleGone")
fun visibleGone(view: View, isShow: Boolean) {
    view.visibility = if (isShow) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
