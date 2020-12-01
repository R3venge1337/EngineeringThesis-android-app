package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
@Entity(tableName = "audio")
data class Audio (

        @JsonProperty("audioId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        var audioId: Int,

        @JsonProperty("audioFileTable")
        val  audioFileTable:AudioFileTable,

        @JsonProperty("audioDownloadUri")
        val audioDownloadUri :String,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean
) {

}