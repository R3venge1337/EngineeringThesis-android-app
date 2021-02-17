package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Gameplay
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

@Dao
interface GameplayDAO {

    @POST("matches")
    fun saveGameplay(@Body gameplayObj:Gameplay):Completable

    @POST("matches")
    fun saveGameplayWithReturnedId(@Body gameplayObj:Gameplay): Single<Gameplay>
}