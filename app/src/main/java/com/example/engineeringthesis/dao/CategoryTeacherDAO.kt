package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.CategoryTeacher
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path


@Dao
interface CategoryTeacherDAO  {

    @GET("categories/teachers/{teacherId}")
    fun getAllCategoriesTeacher(@Path("teacherId") teacherId : Int): Flowable<List<CategoryTeacher>>
}