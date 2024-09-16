/**
 * Created by Yiwen Cheng on 09/16/2024
 */
package com.example.codeassessment.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.codeassessment.R

/**
 * Binding adapter for data binding
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("remote_src")
    fun loadImageFromUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(it)
                .placeholder(R.drawable.ic_launcher_foreground) // placeholder image
                .into(imageView)
        } ?: run {
            imageView.setImageResource(R.drawable.ic_launcher_foreground) // default image
        }
    }
}