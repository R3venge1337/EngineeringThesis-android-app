package com.example.engineeringthesis.repository

import com.example.engineeringthesis.dao.StatisticResultDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class StatisticResultRepository {
    private val statisticResultDAO: StatisticResultDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        statisticResultDAO = retrofitClient!!.create(StatisticResultDAO::class.java)
    }
}