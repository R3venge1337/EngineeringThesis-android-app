package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
@Entity(tableName = "category")
data class Category(

        @JsonProperty("categoryId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
         var categoryId: Int,

        @JsonProperty("categoryName")
        var categoryName: String,

        @JsonProperty("languageId")
        var languageId: Language,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean

) {

    constructor(categoryName: String,languageId: Language,isNew: Boolean,isAccepted: Boolean)
    :this(0,categoryName,languageId,isNew,isAccepted)

    override fun toString(): String {
        return "Category(categoryId=$categoryId, categoryName='$categoryName', languageId=$languageId, isNew=$isNew, isAccepted=$isAccepted)"
    }
}