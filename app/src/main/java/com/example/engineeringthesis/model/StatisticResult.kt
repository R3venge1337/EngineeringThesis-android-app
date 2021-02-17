package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StatisticResult(

        @JsonProperty("statisticResultsId")
        @PrimaryKey(autoGenerate = true)
        val statisticResultsId:Int,

        @JsonProperty("statisticResults")
        val statisticResults:String,

        @JsonProperty("statisticTypeId")
        val  statisticTypeId:StatisticType?
) {
        override fun toString(): String {
                return "StatisticResult(statisticResultsId=$statisticResultsId, " +
                        "statisticResults='$statisticResults', " +
                        "statisticTypeId=$statisticTypeId)"
        }
}