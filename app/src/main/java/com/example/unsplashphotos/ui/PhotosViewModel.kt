package com.example.unsplashphotos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashphotos.network.UnsplashApi
import com.example.unsplashphotos.network.UnsplashPhoto
import kotlinx.coroutines.launch

enum class UnsplashApiStatus { LOADING, ERROR, DONE }

class PhotosViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<UnsplashPhoto>>()
    val photos: LiveData<List<UnsplashPhoto>> = _photos

    private val _photo = MutableLiveData<UnsplashPhoto>()
    val photo: LiveData<UnsplashPhoto> = _photo

    private val _loading = MutableLiveData<UnsplashApiStatus>()
    val loading: LiveData<UnsplashApiStatus> = _loading

    init {
        getUnsplashPhotos()
    }

    private fun getUnsplashPhotos() {
        viewModelScope.launch {
            _loading.value = UnsplashApiStatus.LOADING
            try {
                _photos.value = UnsplashApi.retrofitService.getPhotos()
            } catch (e: Exception) {
                _loading.value = UnsplashApiStatus.ERROR
            }
            _loading.value = UnsplashApiStatus.DONE
        }
    }

    fun setPhoto(photo: UnsplashPhoto) {
        _photo.value = photo
    }
}