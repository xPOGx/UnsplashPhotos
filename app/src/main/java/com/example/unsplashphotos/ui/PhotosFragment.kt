package com.example.unsplashphotos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotosBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerView.adapter = PhotoGridAdapter(PhotoListener { photo ->
            viewModel.setPhoto(photo)
            navigateToDetailFragment()
        })

        return binding.root
    }

    private fun navigateToDetailFragment() {
        val action = R.id.action_photosFragment_to_detailFragment
        findNavController().navigate(action)
    }
}