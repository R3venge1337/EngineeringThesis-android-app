package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.LanguageDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Language
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class LanguageRepository @Inject constructor(application: Application?) {
    private val languageDAO: LanguageDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient = RetrofitClient.retrofit
        languageDAO = retrofitClient!!.create(LanguageDAO::class.java)
    }

    fun allLanguages(): LiveData<List<Language>> {
        return LiveDataReactiveStreams.fromPublisher(languageDAO.allLanguages().subscribeOn(newThread()))
    }

    fun getLanguageByName(langName : String) : Language?
    {
        return languageDAO.getLanguageByName(langName)?.subscribeOn(newThread())?.blockingGet()
    }

    fun saveLanguage(lang:Language)
    {
         languageDAO.saveLanguage(lang).subscribeOn(newThread()).blockingAwait()
    }
}