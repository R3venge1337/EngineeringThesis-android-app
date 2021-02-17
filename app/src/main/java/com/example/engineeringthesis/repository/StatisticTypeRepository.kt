package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.StatisticTypeDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.StatisticType
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class StatisticTypeRepository @Inject constructor(application: Application?) {
    private val statisticTypeDAO: StatisticTypeDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        statisticTypeDAO = retrofitClient!!.create(StatisticTypeDAO::class.java)
    }

    fun getAllStatisticTypes(): List<StatisticType>
    {
        return statisticTypeDAO.getAllStatisticTypes().subscribeOn(Schedulers.newThread()).blockingGet()
    }
}