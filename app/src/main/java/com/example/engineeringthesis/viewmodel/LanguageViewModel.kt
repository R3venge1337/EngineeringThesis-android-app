package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.repository.LanguageRepository

class LanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val languageRepository: LanguageRepository

    init {
        languageRepository = LanguageRepository(application)
    }

    fun allLanguages(): LiveData<List<Language>>{
        return languageRepository.allLanguages()
    }

    fun getLanguageByName(langName: String) : Language?
    {
        return languageRepository.getLanguageByName(langName)
    }

    fun getLanguageById(langId: Int) : Language?
    {
        return languageRepository.getLanguageById(langId)
    }

    fun saveLanguage(lang: Language)
    {
        return languageRepository.saveLanguage(lang)
    }
}