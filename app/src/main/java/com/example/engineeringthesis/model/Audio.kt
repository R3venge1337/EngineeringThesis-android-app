package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class Audio (

        @JsonProperty("audioId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        var audioId: Int,

        @JsonProperty("audioFileTable")
        val  audioFileTable:AudioFileTable,

        @JsonProperty("audioDownloadUri")
        val audioDownloadUri :String,

        @JsonProperty("wordId")
        val wordId:Word,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean
) {

}