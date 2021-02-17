package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class GameplayResult(

        @JsonProperty("gameplayResultsId")
        @PrimaryKey(autoGenerate = true)
        var  gameplayResultsId:Int,
        @JsonProperty("gameplayId")
        var  gameplayId:Gameplay,
        @JsonProperty("statisticResultsId")
        var  statisticResultsId:StatisticResult
){

}