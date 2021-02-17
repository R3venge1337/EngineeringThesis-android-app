package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.GameplayResultDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.GameplayResult
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class GameplayResultRepository@Inject constructor(application: Application?) {

    private val gameplayResultDAO: GameplayResultDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        gameplayResultDAO = retrofitClient!!.create(GameplayResultDAO::class.java)
    }

    fun saveGameplayResult( gameplayResultObj: GameplayResult)
    {
        gameplayResultDAO.saveGameplayResult(gameplayResultObj).subscribeOn(Schedulers.newThread()).blockingAwait()
    }

    fun getGameplayResultByguestUUID(guestUUID:String): LiveData<List<GameplayResult>>
    {
        return LiveDataReactiveStreams.fromPublisher(gameplayResultDAO.getGameplayResultByguestUUID(guestUUID).subscribeOn(Schedulers.newThread()))
    }

    fun getGameplayResultByGameName(gameName:String): LiveData<List<GameplayResult>>
    {
        return LiveDataReactiveStreams.fromPublisher(gameplayResultDAO.getGameplayResultByGameName(gameName).subscribeOn(Schedulers.newThread()))
    }

}