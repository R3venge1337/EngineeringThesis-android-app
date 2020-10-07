package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "game")
data class Game(

        @JsonProperty("gameId")
        @PrimaryKey(autoGenerate = true)
        var gameId: Int,

        @JsonProperty("gameName")
        var gameName:String) {

        override fun toString(): String {
                return "Game(gameId=$gameId, gameName='$gameName')"
        }
}