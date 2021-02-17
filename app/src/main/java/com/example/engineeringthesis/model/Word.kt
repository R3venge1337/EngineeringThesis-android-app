package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class Word(

        @JsonProperty("wordId")
        @PrimaryKey(autoGenerate = true)
        val wordId:Int,

        @JsonProperty("wordName")
        var wordName:String,

        @JsonProperty( "wordDownloadUri")
        val  wordDownloadUri:String?,

        @JsonProperty("categoryId")
        val categoryId:Category,

        @JsonProperty("languageId")
        val languageId:Language,

        @JsonProperty("imageId")
        val imageId: Image?,

        @JsonProperty("audioId")
        val audioId: Audio?
) {
    override fun toString(): String {
        return "Word(wordId=$wordId, wordName='$wordName', wordDownloadUri=$wordDownloadUri, " +
                "categoryId=$categoryId, languageId=$languageId, imageId=$imageId, audioId=$audioId)"
    }
}