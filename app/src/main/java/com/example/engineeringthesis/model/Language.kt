package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime


@Entity(tableName = "language")
data class Language (

        @JsonProperty("languageId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        var languageId: Int,

        @JsonProperty("languageName")
        var languageName: String,

        @JsonProperty("languageCreatedDate")
        @JsonSerialize(using = LocalDateTimeSerializer::class)
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
       // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        var  languageCreatedDate:LocalDateTime,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean

        /*  @JsonProperty("languageImageIcon")
          private var languageImageIcon: String,

         */

){
    constructor(languageName: String,languageCreatedDate:LocalDateTime,isNew: Boolean,isAccepted: Boolean)
    :this(0,languageName,languageCreatedDate,isNew,isAccepted)

    override fun toString(): String {
        return "Language(languageId=$languageId, languageName='$languageName', languageCreatedDate=$languageCreatedDate, isNew=$isNew, isAccepted=$isAccepted)"
    }
}