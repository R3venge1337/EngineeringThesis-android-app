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
    fun saveWord(word: Word)
    {
        wordRepository.saveWord(word)
    }
    fun getWordById(wordInt: Int):Word
    {
        return wordRepository.getWordById(wordInt)
    }
    fun getWordByName(wordName: String):Word
    {
        return wordRepository.getWordByName(wordName)
    }
    fun updateWord(wordInt: Int,wordObj:Word)
    {
        wordRepository.updateWord(wordInt,wordObj)
    }
}