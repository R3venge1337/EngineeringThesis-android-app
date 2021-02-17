package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.StatisticResultDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.StatisticResult
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class StatisticResultRepository @Inject constructor(application: Application?) {
    private val statisticResultDAO: StatisticResultDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        statisticResultDAO = retrofitClient!!.create(StatisticResultDAO::class.java)
    }

    fun saveStatisticResult(result : StatisticResult)
    {
        statisticResultDAO.saveStatisticResult(result)
                .subscribeOn(Schedulers.newThread()).blockingAwait()
    }
    fun saveStatisticResultWithReturnedId( result : StatisticResult): StatisticResult
    {
       return statisticResultDAO.saveStatisticResultWithReturnedId(result)
               .subscribeOn(Schedulers.newThread()).blockingGet()
    }
}