package com.example.nux.news.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatter(): String {
    val dateFormatter = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return dateFormatter.format(this)
}
