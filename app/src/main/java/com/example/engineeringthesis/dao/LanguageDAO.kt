package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Language
import io.reactivex.Flowable
import retrofit2.http.GET

@Dao
interface LanguageDAO {

    @get:GET("languages")
    val allLanguages: Flowable<List<Language>>
}