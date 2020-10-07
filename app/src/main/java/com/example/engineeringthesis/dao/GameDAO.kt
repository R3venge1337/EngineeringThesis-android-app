package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.Game
import io.reactivex.Flowable
import retrofit2.http.GET


@Dao
interface GameDAO {

    @get:GET("games")
    val getAllGames : Flowable<List<Game>>
}