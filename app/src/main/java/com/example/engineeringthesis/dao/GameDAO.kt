package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Game
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET


@Dao
interface GameDAO {

    @GET("games")
    fun getAllGames() : Flowable<List<Game>>

    @GET("games")
    fun getAllGamesSingle() : Single<List<Game>>
}