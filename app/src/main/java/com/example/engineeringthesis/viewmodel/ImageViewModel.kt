package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.repository.ImageRepository
import okhttp3.RequestBody

class ImageViewModel(application:Application) : AndroidViewModel(application) {
    private val imageRepository: ImageRepository

    init {
        imageRepository = ImageRepository(application)
    }

    fun saveImage(file: RequestBody)
    {
        imageRepository.saveImage(file)
    }

}