package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Language
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Dao
interface LanguageDAO {

    @GET("languages")
    fun allLanguages(): Flowable<List<Language>>

    @GET("languages")
    fun getLanguageByName(@Query("languageName") languageName : String): Single<Language?>?

    @GET("languages")
    fun getLanguageById(@Query("languageId") languageId : Int): Single<Language?>?

    @POST("languages")
    fun saveLanguage(@Body lang : Language): Completable
}