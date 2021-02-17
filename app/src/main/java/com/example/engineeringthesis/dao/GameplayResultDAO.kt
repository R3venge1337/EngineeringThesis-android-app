package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.GameplayResult
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@Dao
interface GameplayResultDAO {

    @POST("results")
    fun saveGameplayResult(@Body gameplayResultObj: GameplayResult): Completable

    @GET("results/child/{guestUUID}")
    fun getGameplayResultByguestUUID(@Path("guestUUID") guestUUID:String): Flowable<List<GameplayResult>>

    @GET("results/game/{gameName}")
    fun getGameplayResultByGameName(@Path("gameName") gameName:String): Flowable<List<GameplayResult>>

}