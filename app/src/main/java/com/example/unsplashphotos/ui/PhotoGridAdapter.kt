package com.example.unsplashphotos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphotos.databinding.GridViewPhotoBinding
import com.example.unsplashphotos.network.UnsplashPhoto

class PhotoGridAdapter(private val clickListener: PhotoListener) :
    ListAdapter<UnsplashPhoto, PhotoGridAdapter.UnsplashPhotoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.photoData.imgSrcUrl == newItem.photoData.imgSrcUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        return UnsplashPhotoViewHolder(
            GridViewPhotoBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val unsplashPhoto = getItem(position)
        holder.bind(clickListener, unsplashPhoto)
    }

    class UnsplashPhotoViewHolder(private var binding: GridViewPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PhotoListener, Unsplash: UnsplashPhoto) {
            binding.photo = Unsplash
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class PhotoListener(val clickListener: (photo: UnsplashPhoto) -> Unit) {

    fun onClick(photo: UnsplashPhoto) {
        clickListener(photo)
    }
}