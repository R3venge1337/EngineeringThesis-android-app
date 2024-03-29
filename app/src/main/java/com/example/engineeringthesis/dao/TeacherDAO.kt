package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Teacher
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

@Dao
interface TeacherDAO {

    @GET("teachers")
    fun getTeachersByLanguageName(@Query("languageName") languageName : String): Flowable<List<Teacher>>

    @POST("teachers")
    fun saveTeacher(@Body teacher: Teacher): Completable

    @GET("teachers/accounts")
    fun getTeacherWithAccount(@Query("accountName") accountName : String): Single<Teacher>

    @PUT("teachers/{teacherId}")
    fun updateTeacher(@Body teacher: Teacher,
                      @Path("teacherId") teacherId : Int): Completable


}