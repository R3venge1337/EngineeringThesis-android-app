package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "image")
data class Image(

        @JsonProperty("imageId")
        @PrimaryKey(autoGenerate = true)
        val imageId:Int,

        @JsonProperty("imageDownloadUri")
        var imageDownloadUri :String,

        @JsonProperty("imageFileTable")
        val  imageFileTable:ImageFileTable,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean
) {


}