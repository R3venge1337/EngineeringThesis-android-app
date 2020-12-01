package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Teacher
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@Dao
interface TeacherDAO {

    @GET("teachers")
    fun getTeachersByLanguageName(@Query("languageName") languageName : String): Flowable<List<Teacher>>

    @POST("teachers")
    fun saveTeacher(@Body teacher: Teacher): Completable


}