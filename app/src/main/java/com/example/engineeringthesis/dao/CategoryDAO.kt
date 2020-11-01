package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Category
import io.reactivex.Flowable
import retrofit2.http.GET

@Dao
interface CategoryDAO {

    @GET("categories")
    fun allCategories(): Flowable<List<Category>>
}