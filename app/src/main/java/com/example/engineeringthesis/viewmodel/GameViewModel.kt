package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Game
import com.example.engineeringthesis.repository.GameRepository

class GameViewModel(application: Application)  : AndroidViewModel(application) {
 private val gameRepository: GameRepository

  init {
  gameRepository = GameRepository(application)
  }

 fun allGames(): LiveData<List<Game>> {
   return gameRepository.allGames()
  }
 fun allGamesSingle(): List<Game> {
  return gameRepository.allGamesSingle()
 }
}