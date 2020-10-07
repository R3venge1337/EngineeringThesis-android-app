package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.LanguageDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Language
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class LanguageRepository @Inject constructor(application: Application?) {
    private val languageDAO: LanguageDAO
    private var retrofitClient: Retrofit?
    val allLanguages: LiveData<List<Language>>
        get() = LiveDataReactiveStreams.fromPublisher(languageDAO.allLanguages.subscribeOn(Schedulers.newThread()))
    init {
        retrofitClient = RetrofitClient.retrofit
        languageDAO = retrofitClient!!.create(LanguageDAO::class.java)
    }
}