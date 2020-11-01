package com.example.engineeringthesis.repository

import com.example.engineeringthesis.dao.ImageDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class ImageRepository {
    private val imageDAO: ImageDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        imageDAO = retrofitClient!!.create(ImageDAO::class.java)
    }
}