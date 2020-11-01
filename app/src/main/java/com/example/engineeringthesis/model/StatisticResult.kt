package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class StatisticResult(

        @JsonProperty("statisticResultsId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        val statisticResultsId:Int,

        @JsonProperty("statisticResults")
        val statisticResults:String,

        @JsonProperty("statisticTypeId")
        val  statisticTypeId:StatisticType
) {
}