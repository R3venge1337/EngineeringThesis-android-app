package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Teacher
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

@Dao
interface TeacherDAO {

    @POST("teachers")
    fun saveTeacher(@Body teacher: Teacher): Completable
}