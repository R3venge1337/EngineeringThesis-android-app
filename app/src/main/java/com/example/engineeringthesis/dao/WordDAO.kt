package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Word
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

@Dao
interface WordDAO {

    @GET("words/categories")
    fun getAllWordsFromCategory(@Query("categoryName") categoryName:String,
                                @Query("pageNumber") pageNumber:Int,
                                @Query("size") size:Int) : Single<List<Word>>

    @POST("words" )
    fun saveWord(@Body word: Word): Completable

    @GET("words/{wordId}")
    fun getWordById(@Path("wordId") wordId:Int) : Single<Word>

    @GET("words")
    fun getWordByName(@Query("wordName") wordName:String) : Single<Word>

    @PUT("words/{wordId}")
    fun updateWord(@Path("wordId") wordId:Int,wordObj:Word): Completable
}