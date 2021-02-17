package com.example.engineeringthesis.dao

import androidx.room.Dao
import io.reactivex.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.*

@Dao
interface ImageDAO {
    @Multipart
    @POST("images")
    fun saveImage(@Part("file") file:RequestBody): Completable

    @GET
    fun downloadImageByName(@Path("fileName") fileName:String): Single<RequestBody>

}