package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.GameplayResult
import com.example.engineeringthesis.repository.GameplayResultRepository

class GameplayResultViewModel(application: Application) : AndroidViewModel(application) {
    private val gameplayResultRepository: GameplayResultRepository
    init {
        gameplayResultRepository = GameplayResultRepository(application)
    }
    fun saveGameplayResult(gameplayResultObj: GameplayResult)
    {
        gameplayResultRepository.saveGameplayResult(gameplayResultObj)
    }
    fun getGameplayResultByGuestUUID(guestUUID:String): LiveData<List<GameplayResult>>
    {
       return gameplayResultRepository.getGameplayResultByguestUUID(guestUUID)
    }

    fun getGameplayResultByGameName(gameName:String): LiveData<List<GameplayResult>>
    {
        return gameplayResultRepository.getGameplayResultByGameName(gameName)
    }



}