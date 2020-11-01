package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.GameDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Game
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class GameRepository @Inject constructor(application: Application?){
    private val gameDAO: GameDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        gameDAO = retrofitClient!!.create(GameDAO::class.java)
    }

    fun allGames(): LiveData<List<Game>> {
        return LiveDataReactiveStreams.fromPublisher(gameDAO.getAllGames().subscribeOn(newThread()))
    }

}