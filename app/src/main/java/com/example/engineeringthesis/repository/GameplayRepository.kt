package com.example.engineeringthesis.repository

import androidx.room.Dao
import com.example.engineeringthesis.dao.GameplayDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class GameplayRepository {
    private val gameplayDAO: GameplayDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        gameplayDAO = retrofitClient!!.create(GameplayDAO::class.java)
    }
}