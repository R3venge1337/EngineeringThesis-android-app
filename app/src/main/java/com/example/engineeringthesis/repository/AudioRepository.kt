package com.example.engineeringthesis.repository

import com.example.engineeringthesis.dao.AudioDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class AudioRepository {
    private val audioDAO: AudioDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        audioDAO = retrofitClient!!.create(AudioDAO::class.java)
    }
}