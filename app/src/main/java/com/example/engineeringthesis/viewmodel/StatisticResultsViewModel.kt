package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.StatisticResult
import com.example.engineeringthesis.repository.StatisticResultRepository

class StatisticResultsViewModel(application: Application): AndroidViewModel(application) {
    private val statisticResultRepository: StatisticResultRepository
    init {
        statisticResultRepository = StatisticResultRepository(application)
    }
    fun saveStatisticResult(result : StatisticResult)
    {
        statisticResultRepository.saveStatisticResult(result)
    }
    fun saveStatisticResultWithReturnedId( result : StatisticResult): StatisticResult
    {
       return statisticResultRepository.saveStatisticResultWithReturnedId(result)
    }
}