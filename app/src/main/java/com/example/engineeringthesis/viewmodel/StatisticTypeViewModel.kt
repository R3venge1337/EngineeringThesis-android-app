package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.StatisticType
import com.example.engineeringthesis.repository.StatisticTypeRepository

class StatisticTypeViewModel(application: Application): AndroidViewModel(application) {
    private val statisticTypeRepository: StatisticTypeRepository
     init {
         statisticTypeRepository = StatisticTypeRepository(application)
     }
    fun getAllStatisticTypes(): List<StatisticType>
    {
        return statisticTypeRepository.getAllStatisticTypes()
    }
}