package com.sopt.aladinaos.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun ImageView.setImage(imgUrl: String?) {
        this.let {
            Glide.with(context)
                .load(imgUrl)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("setCoverImage")
    fun ImageView.setCoverImage(imgUrl: String?) {
        this.let {
            Glide.with(context)
                .load(imgUrl)
                .transform(CenterCrop(), RoundedCorners(14))
                .into(this)
        }
    }
}
