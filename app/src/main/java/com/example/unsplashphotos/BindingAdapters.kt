package com.example.unsplashphotos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.unsplashphotos.network.UnsplashPhoto
import com.example.unsplashphotos.ui.PhotoGridAdapter
import com.example.unsplashphotos.ui.UnsplashApiStatus
import com.example.unsplashphotos.ui.UnsplashApiStatus.*

@BindingAdapter("listData")
fun bindRecycleView(recyclerView: RecyclerView, data: List<UnsplashPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("loading")
fun bindLoading(imgView: ImageView, status: UnsplashApiStatus) {
    when (status) {
        LOADING, ERROR -> imgView.visibility = View.VISIBLE
        DONE -> imgView.visibility = View.GONE
    }
}

@BindingAdapter("description")
fun bindDescription(textView: TextView, description: String?) {
    if (description.isNullOrEmpty()) {
        textView.visibility = View.GONE
    } else {
        textView.visibility = View.VISIBLE
        textView.text = description
    }
}

