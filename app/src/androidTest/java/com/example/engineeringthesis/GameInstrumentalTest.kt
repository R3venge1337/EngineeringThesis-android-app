package com.example.engineeringthesis

import androidx.test.core.app.ApplicationProvider
import com.example.engineeringthesis.viewmodel.GameViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class GameInstrumentalTest {
   private lateinit var gameViewModel:GameViewModel

   @Test
    fun shouldGetAllGames( )
    {
        gameViewModel = GameViewModel(ApplicationProvider.getApplicationContext())
        var gameList = gameViewModel.allGames()
        assertThat(gameList, Matchers.notNullValue())
    }
}