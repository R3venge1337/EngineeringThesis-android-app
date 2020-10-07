package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Language
import com.example.engineeringthesis.repository.LanguageRepository

class LanguageViewModel(application: Application) : AndroidViewModel(application) {
    private val languageRepository: LanguageRepository
    val allLanguages: LiveData<List<Language>>
        get() = languageRepository.allLanguages

    init {
        languageRepository = LanguageRepository(application)
    }
}