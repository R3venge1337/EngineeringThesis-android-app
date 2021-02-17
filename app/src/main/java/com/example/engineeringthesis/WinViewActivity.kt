package com.example.engineeringthesis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.*
import com.example.engineeringthesis.utils.GlobalValues.pageNumberDAD
import com.example.engineeringthesis.utils.GlobalValues.pageNumberFOP
import com.example.engineeringthesis.utils.GlobalValues.pageNumberFOV
import com.example.engineeringthesis.utils.GlobalValues.pageNumberMGA
import com.example.engineeringthesis.utils.GlobalValues.pageNumberSAA
import com.example.engineeringthesis.viewmodel.*
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_win_view.*
import java.time.LocalDateTime
import javax.inject.Inject

class WinViewActivity : DaggerAppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var statisticTypeViewModel: StatisticTypeViewModel
    private lateinit var statisticResultViewModel: StatisticResultsViewModel
    private lateinit var  gameplayViewModel: GameplayViewModel
    private lateinit var  gameplayResultViewModel: GameplayResultViewModel
    private var gameFromShared: String? = ""
    private lateinit var gameObj: Game
    private lateinit var langObj: Language
    private lateinit var catObj: CategoryTeacher
    private var guestId:String = " "
    private var gamesAll: List<Game> = ArrayList()
    private var statisticsAll: List<StatisticType> = ArrayList()
    private lateinit var gameplayObj:Gameplay
    private lateinit var gameplayWithId:Gameplay
    private lateinit var resultShotsObj:StatisticResult
    private lateinit var resultTimeObj:StatisticResult
    private lateinit var savedResultShotsWithId:StatisticResult
    private lateinit var savedResultTimeWithId:StatisticResult
    private var gameShots:String = " "
    private var gameTime:String = " "

    @Inject
    lateinit var sharedPreferences: SharedPreferences;

    @Inject
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor;

    @Inject
    lateinit var jacksonMapper: ObjectMapper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_view)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        statisticTypeViewModel = ViewModelProvider(this).get(StatisticTypeViewModel::class.java)
        gameplayViewModel = ViewModelProvider(this).get(GameplayViewModel::class.java)
        statisticResultViewModel = ViewModelProvider(this).get(StatisticResultsViewModel::class.java)
        gameplayResultViewModel = ViewModelProvider(this).get(GameplayResultViewModel::class.java)
        gamesAll = gameViewModel.allGamesSingle()
        getDataFromSharedPref()
        saveGameplayInformation()
        statisticsAll = statisticTypeViewModel.getAllStatisticTypes()
        winView_summary_gameName.text = gameFromShared
        when(gameFromShared)
        {
            gamesAll[0].gameName -> {
                val retShots:StatisticType =  getStaticType("Błędy")
                val rettime:StatisticType =   getStaticType("Czas w rozgrywce")
                gameShots = sharedPreferences.getString("fop_shots", "")!!
                gameTime = sharedPreferences.getString("fop_time", "")!!
                winView_summary_gameTime.text = "Czas w grze:  $gameTime"
                winView_summary_wrongAnswers.text = "Liczba błędów: $gameShots"
                resultShotsObj = StatisticResult(0,gameShots,retShots)
                resultTimeObj = StatisticResult(0,gameTime,rettime)
                savedResultShotsWithId = statisticResultViewModel.saveStatisticResultWithReturnedId(resultShotsObj)
                savedResultTimeWithId =  statisticResultViewModel.saveStatisticResultWithReturnedId(resultTimeObj)
                val gameplayResultShots = GameplayResult(0, gameplayWithId, savedResultShotsWithId)
                val gameplayResultTime = GameplayResult(0, gameplayWithId,  savedResultTimeWithId)
                gameplayResultViewModel.saveGameplayResult(gameplayResultShots)
                gameplayResultViewModel.saveGameplayResult(gameplayResultTime)
            }
            gamesAll[1].gameName -> {
                val retShots:StatisticType =  getStaticType("Błędy")
                val rettime:StatisticType =   getStaticType("Czas w rozgrywce")
                gameShots = sharedPreferences.getString("fov_shots", "")!!
                gameTime = sharedPreferences.getString("fov_time", "")!!
                winView_summary_gameTime.text = "Czas w grze:  $gameTime"
                winView_summary_wrongAnswers.text = "Liczba błędów: $gameShots"
                resultShotsObj = StatisticResult(0,gameShots,retShots)
                resultTimeObj = StatisticResult(0,gameTime,rettime)
                savedResultShotsWithId = statisticResultViewModel.saveStatisticResultWithReturnedId(resultShotsObj)
                savedResultTimeWithId =  statisticResultViewModel.saveStatisticResultWithReturnedId(resultTimeObj)
                val gameplayResultShots = GameplayResult(0, gameplayWithId, savedResultShotsWithId)
                val gameplayResultTime = GameplayResult(0, gameplayWithId,  savedResultTimeWithId)
                gameplayResultViewModel.saveGameplayResult(gameplayResultShots)
                gameplayResultViewModel.saveGameplayResult(gameplayResultTime)
            }
            gamesAll[2].gameName -> {
                val retShots:StatisticType =  getStaticType("Błędy")
                val rettime:StatisticType =   getStaticType("Czas w rozgrywce")
                gameShots = sharedPreferences.getString("dad_shots", "")!!
                gameTime = sharedPreferences.getString("dad_time", "")!!
                winView_summary_gameTime.text = "Czas w grze:  $gameTime"
                winView_summary_wrongAnswers.text = "Liczba błędów: $gameShots"
                resultShotsObj = StatisticResult(0,gameShots,retShots)
                resultTimeObj = StatisticResult(0,gameTime,rettime)
                savedResultShotsWithId = statisticResultViewModel.saveStatisticResultWithReturnedId(resultShotsObj)
                savedResultTimeWithId =  statisticResultViewModel.saveStatisticResultWithReturnedId(resultTimeObj)
                val gameplayResultShots = GameplayResult(0, gameplayWithId, savedResultShotsWithId)
                val gameplayResultTime = GameplayResult(0, gameplayWithId,  savedResultTimeWithId)
                gameplayResultViewModel.saveGameplayResult(gameplayResultShots)
                gameplayResultViewModel.saveGameplayResult(gameplayResultTime)
            }
            gamesAll[3].gameName -> {
                val retShots:StatisticType =  getStaticType("Błędy")
                val rettime:StatisticType =   getStaticType("Czas w rozgrywce")
                gameShots = sharedPreferences.getString("mg_shots", "")!!
                gameTime = sharedPreferences.getString("mg_time", "")!!
                winView_summary_gameTime.text = "Czas w grze:  $gameTime"
                winView_summary_wrongAnswers.text = "Liczba błędów: $gameShots"
                resultShotsObj = StatisticResult(0,gameShots,retShots)
                resultTimeObj = StatisticResult(0,gameTime,rettime)
                savedResultShotsWithId = statisticResultViewModel.saveStatisticResultWithReturnedId(resultShotsObj)
                savedResultTimeWithId =  statisticResultViewModel.saveStatisticResultWithReturnedId(resultTimeObj)
                val gameplayResultShots = GameplayResult(0, gameplayWithId, savedResultShotsWithId)
                val gameplayResultTime = GameplayResult(0, gameplayWithId,  savedResultTimeWithId)
                gameplayResultViewModel.saveGameplayResult(gameplayResultShots)
                gameplayResultViewModel.saveGameplayResult(gameplayResultTime)
            }
            gamesAll[4].gameName -> {
                val retShots:StatisticType =  getStaticType("Błędy")
                val rettime:StatisticType =   getStaticType("Czas w rozgrywce")
                gameShots = sharedPreferences.getString("saa_shots", "")!!
                gameTime = sharedPreferences.getString("saa_time", "")!!
                winView_summary_gameTime.text = "Czas w grze:  $gameTime"
                winView_summary_wrongAnswers.text = "Liczba błędów: $gameShots"
                resultShotsObj = StatisticResult(0,gameShots,retShots)
                resultTimeObj = StatisticResult(0,gameTime,rettime)
                savedResultShotsWithId = statisticResultViewModel.saveStatisticResultWithReturnedId(resultShotsObj)
                savedResultTimeWithId =  statisticResultViewModel.saveStatisticResultWithReturnedId(resultTimeObj)
                val gameplayResultShots = GameplayResult(0, gameplayWithId, savedResultShotsWithId)
                val gameplayResultTime = GameplayResult(0, gameplayWithId,  savedResultTimeWithId)
                gameplayResultViewModel.saveGameplayResult(gameplayResultShots)
                gameplayResultViewModel.saveGameplayResult(gameplayResultTime)
            }
        }


        winViewActivityButton.setOnClickListener {
            when(gameFromShared)
            {
                gamesAll[0].gameName -> {
                    pageNumberFOP++
                    val intent = Intent(this, FindOutPictureActivity::class.java)
                    startActivity(intent)
                }
                gamesAll[1].gameName -> {
                    pageNumberFOV++
                    val intent = Intent(this, FindOutVocabularyActivity::class.java)
                    startActivity(intent)
                }
                gamesAll[2].gameName -> {
                    pageNumberDAD++
                    val intent = Intent(this, DragAndDropActivity::class.java)
                    startActivity(intent)
                }
                gamesAll[3].gameName -> {
                    pageNumberMGA++
                    val intent = Intent(this, MemoryGameActivity::class.java)
                    startActivity(intent)
                }
                gamesAll[4].gameName -> {
                    pageNumberSAA++
                    val intent = Intent(this, SelectAndAdjustActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
    fun getDataFromSharedPref() {
        gameFromShared = sharedPreferences.getString("game_selected", "")
        gameObj = gamesAll.stream().filter { game -> game.gameName == gameFromShared }.findFirst().get()
        langObj = jacksonMapper.readValue(sharedPreferences.getString("language_selected", ""), Language::class.java)
        catObj = jacksonMapper.readValue(sharedPreferences.getString("category_selected", ""), CategoryTeacher::class.java)
        guestId = sharedPreferences.getString("questUniqueId", "")!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveGameplayInformation()
    {
        gameplayObj = Gameplay(0,langObj,gameObj,catObj.categoryId, LocalDateTime.now(), LocalDateTime.now(),guestId)
        gameplayWithId = gameplayViewModel.saveGameplayWithReturnedId(gameplayObj)
    }

    fun getStaticType(name:String):StatisticType
    {
       return  statisticsAll.stream().filter { res -> res.statisticName == name }.findFirst().get()
    }

}