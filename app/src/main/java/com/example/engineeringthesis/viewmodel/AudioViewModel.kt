package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.repository.AudioRepository
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class AudioViewModel(application: Application):AndroidViewModel(application) {
    private val audioRepository: AudioRepository

    init {
        audioRepository = AudioRepository(application)
    }
    fun saveAudio(file: RequestBody)
    {
        audioRepository.saveAudio(file)
    }
    fun downloadAudio(fileName:String): Response<ResponseBody>
    {
        return audioRepository.downloadAudioByName(fileName)
    }
}