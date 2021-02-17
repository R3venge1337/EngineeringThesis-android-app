package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.GameplayDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Gameplay
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class GameplayRepository  @Inject constructor(application: Application?) {
    private val gameplayDAO: GameplayDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        gameplayDAO = retrofitClient!!.create(GameplayDAO::class.java)
    }

    fun saveGameplay(gameplayObj:Gameplay)
    {
        gameplayDAO.saveGameplay(gameplayObj).subscribeOn(Schedulers.newThread()).blockingAwait()
    }

    fun saveGameplayWithReturnedId(gameplayObj:Gameplay): Gameplay
    {
        return gameplayDAO.saveGameplayWithReturnedId(gameplayObj).subscribeOn(Schedulers.newThread()).blockingGet()
    }
}