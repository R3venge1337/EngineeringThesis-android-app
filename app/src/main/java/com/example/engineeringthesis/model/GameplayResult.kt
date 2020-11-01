package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class GameplayResult(

        @JsonProperty("gameplayResultsId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
       var  gameplayResultsId:Int,
        @JsonProperty("gameplayId")
       var  gameplayId:Gameplay,

       var  statisticResultsId:StatisticResult
){

}