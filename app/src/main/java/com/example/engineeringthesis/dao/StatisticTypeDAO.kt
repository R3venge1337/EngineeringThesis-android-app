package com.example.engineeringthesis.dao

import androidx.room.Dao
import com.example.engineeringthesis.model.StatisticType
import io.reactivex.Single
import retrofit2.http.GET

@Dao
interface StatisticTypeDAO {

    @GET("statistics")
    fun getAllStatisticTypes(): Single<List<StatisticType>>
}