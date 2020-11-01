package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class Image(

        @JsonProperty("imageId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        val imageId:Int,

        @JsonProperty("imageDownloadUri")
        var imageDownloadUri :String,

        @JsonProperty("imageFileTable")
        val  imageFileTable:ImageFileTable,

        @JsonProperty("wordId")
        var wordId:Word,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean
) {


}