package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class StatisticType(

        @JsonProperty("statisticId")
        @PrimaryKey(autoGenerate = true)
        var statisticId: Int,

        @JsonProperty("statisticName")
        var statisticName: String
) {
    override fun toString(): String {
        return "StatisticType(statisticId=$statisticId, statisticName='$statisticName')"
    }
}