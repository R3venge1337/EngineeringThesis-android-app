package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Category
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Dao
interface CategoryDAO {

    @GET("categories")
    fun allCategories(): Flowable<List<Category>>

    @POST("categories" )
    fun saveCategory(@Body category: Category): Completable

    @GET("categories")
    fun getCategory(@Query("categoryName") categoryName:String): Single<Category>

}