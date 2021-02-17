package com.example.engineeringthesis.repository


import android.app.Application
import com.example.engineeringthesis.dao.WordDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Word
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class WordRepository @Inject constructor(application: Application?) {
    private val wordDAO: WordDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        wordDAO = retrofitClient!!.create(WordDAO::class.java)
    }

    fun getWordsFromCategory(categoryName:String,pageNumber:Int,size:Int): Single<List<Word>>
    {
      return (wordDAO.getAllWordsFromCategory(categoryName,pageNumber,size).subscribeOn(Schedulers.newThread()))
    }

    fun saveWord(word: Word)
    {
        wordDAO.saveWord(word).subscribeOn(Schedulers.newThread()).blockingAwait()
    }

    fun getWordById(wordId:Int):Word
    {
       return wordDAO.getWordById(wordId).subscribeOn(Schedulers.newThread()).blockingGet()
    }
    fun getWordByName(wordName:String):Word
    {
        return wordDAO.getWordByName(wordName).subscribeOn(Schedulers.newThread()).blockingGet()
    }
    fun updateWord(wordId:Int,wordObj:Word)
    {
        return wordDAO.updateWord(wordId,wordObj).subscribeOn(Schedulers.newThread()).blockingAwait()
    }

}