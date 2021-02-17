package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.ImageDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import retrofit2.Retrofit
import javax.inject.Inject

class ImageRepository @Inject constructor(application: Application?) {
    private val imageDAO: ImageDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        imageDAO = retrofitClient!!.create(ImageDAO::class.java)
    }

    fun saveImage(file:RequestBody)
    {
        imageDAO.saveImage(file).subscribeOn(Schedulers.newThread()).blockingAwait()
    }
}