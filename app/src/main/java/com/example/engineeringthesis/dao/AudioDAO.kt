package com.example.engineeringthesis.dao

import androidx.room.Dao
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

@Dao
interface AudioDAO {

    @Multipart
    @POST("audio")
    fun saveAudio(@Part("file") file: RequestBody): Completable

    @GET("audio/downloadFile/{fileName}")
    fun downloadAudioByName(@Path("fileName") fileName:String): Observable<Response<ResponseBody>>

}