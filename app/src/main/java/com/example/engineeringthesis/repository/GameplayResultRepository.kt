package com.example.engineeringthesis.repository

import com.example.engineeringthesis.dao.GameplayResultDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class GameplayResultRepository {

    private val gameplayResultDAO: GameplayResultDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        gameplayResultDAO = retrofitClient!!.create(GameplayResultDAO::class.java)
    }
}