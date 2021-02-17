package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

data class Gameplay(

        @JsonProperty("gameplayId")
        @PrimaryKey(autoGenerate = true)
        val  gameplayId:Int,

        @JsonProperty("languageId")
        val   languageId: Language,

        @JsonProperty("gameId")
        val  gameId:Game,

        @JsonProperty("categoryId")
        val  categoryId:Category,
/*
        @JsonProperty("childId")
        val  childId:Child,
*/
        @JsonProperty("gameMatchDataStart")
        @JsonSerialize(using = LocalDateTimeSerializer::class)
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
        val gameMatchDataStart:LocalDateTime,

        @JsonProperty("gameMatchDataEnd")
        @JsonSerialize(using = LocalDateTimeSerializer::class)
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
        val  gameMatchDataEnd:LocalDateTime,

        @JsonProperty(  "questUUID")
        val questUUID:String
) {
    override fun toString(): String {
        return "Gameplay(gameplayId=$gameplayId, languageId=$languageId, gameId=$gameId, categoryId=$categoryId," +
                " gameMatchDataStart=$gameMatchDataStart, gameMatchDataEnd=$gameMatchDataEnd, questUUID='$questUUID')"
    }
}