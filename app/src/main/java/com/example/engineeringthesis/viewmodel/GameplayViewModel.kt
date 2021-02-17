package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Gameplay
import com.example.engineeringthesis.repository.GameplayRepository

class GameplayViewModel(application: Application) : AndroidViewModel(application) {
    private val gameplayRepository:GameplayRepository

    init {
        gameplayRepository = GameplayRepository(application)
    }

    fun saveGameplay(gameplayObj:Gameplay)
    {
        gameplayRepository.saveGameplay(gameplayObj)
    }

    fun saveGameplayWithReturnedId(gameplayObj:Gameplay): Gameplay
    {
        return gameplayRepository.saveGameplayWithReturnedId(gameplayObj)
    }
}