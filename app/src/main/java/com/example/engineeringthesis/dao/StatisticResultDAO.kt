package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.StatisticResult
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

@Dao
interface StatisticResultDAO {

    @POST("statistics/statisticResults")
    fun saveStatisticResult(@Body result : StatisticResult): Completable

    @POST("statistics/statisticResults")
    fun saveStatisticResultWithReturnedId(@Body result : StatisticResult): Single<StatisticResult>
}