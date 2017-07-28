package com.example.nux.news.util

import android.databinding.BindingAdapter
import android.support.annotation.NonNull
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.nux.news.extension.formatter
import java.util.*

@BindingAdapter("visibleGone")
fun visibleGone(view: View, isShow: Boolean) {
    view.visibility = if (isShow) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImageFromUrl(imageView: ImageView, @NonNull url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("textDate")
fun textDate(textView: TextView, @NonNull date: Date) {
    textView.text = date.formatter()
}
