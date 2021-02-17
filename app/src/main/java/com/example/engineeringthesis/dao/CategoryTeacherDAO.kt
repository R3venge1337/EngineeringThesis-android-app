package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.CategoryTeacher
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


@Dao
interface CategoryTeacherDAO  {

    @GET("categories/teachers/{teacherId}")
    fun getAllCategoriesTeacher(@Path("teacherId") teacherId : Int): Flowable<List<CategoryTeacher>>

    @GET("categories/teachers/{teacherId}")
    fun getAllCategoriesTeacherSingle(@Path("teacherId") teacherId : Int): Single<List<CategoryTeacher>>

    @POST("categories/teachers")
    fun saveCategoryTeacher(@Body categoryTeacher: CategoryTeacher):Completable
}