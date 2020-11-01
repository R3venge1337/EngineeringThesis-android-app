package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Child
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

@Dao
interface ChildDAO {

    @POST("children" )
    fun saveChild(@Body child : Child): Completable
}