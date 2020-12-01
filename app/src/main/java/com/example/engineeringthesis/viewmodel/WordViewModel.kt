package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.repository.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository: WordRepository

    init {
        wordRepository = WordRepository(application)
    }


    fun getWordsFromCategory(categoryName: String,pageNumber:Int,size:Int): List<Word>
    {
        return wordRepository.getWordsFromCategory(categoryName,pageNumber,size).blockingGet()
    }
}