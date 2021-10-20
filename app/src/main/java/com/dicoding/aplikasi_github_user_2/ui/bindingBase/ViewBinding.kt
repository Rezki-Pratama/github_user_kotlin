package com.dicoding.aplikasi_github_user_2.ui.bindingBase

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.dicoding.aplikasi_github_user_2.utils.gone

@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String) {
    // Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    view.gone(shouldBeGone)
}