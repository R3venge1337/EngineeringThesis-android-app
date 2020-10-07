package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Game
import com.example.engineeringthesis.repository.GameRepository

class GameViewModel(application: Application)  : AndroidViewModel(application) {
 private val gameRepository: GameRepository

 val allGames: LiveData<List<Game>>
  get() = gameRepository.allGames

  init {
  gameRepository = GameRepository(application)
  }
}