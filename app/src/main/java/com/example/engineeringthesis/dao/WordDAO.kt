package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Word
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

@Dao
interface WordDAO {

    @GET("words/categories")
    fun getAllWordsFromCategory(@Query("categoryName") categoryName:String,
                                @Query("pageNumber") pageNumber:Int,
                                @Query("size") size:Int
                                ) : Single<List<Word>>
}