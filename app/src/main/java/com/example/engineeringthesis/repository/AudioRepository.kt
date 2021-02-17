package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.AudioDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import io.reactivex.schedulers.Schedulers.newThread
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class AudioRepository @Inject constructor(application: Application?) {
    private val audioDAO: AudioDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        audioDAO = retrofitClient!!.create(AudioDAO::class.java)
    }


    fun saveAudio( file: RequestBody)
    {
        audioDAO.saveAudio(file).subscribeOn(newThread()).blockingAwait()
    }

    fun downloadAudioByName(fileName: String): Response<ResponseBody>
    {
      return audioDAO.downloadAudioByName(fileName).subscribeOn(newThread()).blockingFirst()
    }
}