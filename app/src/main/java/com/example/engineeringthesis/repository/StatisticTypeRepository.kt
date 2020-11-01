package com.example.engineeringthesis.repository

import com.example.engineeringthesis.dao.StatisticTypeDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import retrofit2.Retrofit

class StatisticTypeRepository {
    private val statisticTypeDAO: StatisticTypeDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        statisticTypeDAO = retrofitClient!!.create(StatisticTypeDAO::class.java)
    }
}