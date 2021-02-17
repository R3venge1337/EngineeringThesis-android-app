package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "child")
data class Child(
        @JsonProperty("childId")
        @PrimaryKey(autoGenerate = true)
        var childId: Int,

        @JsonProperty("childName")
        var childName: String,

        @JsonProperty("childSurname")
        var childSurname: String,

        @JsonProperty("childYearBirth")
        var childYearBirth: Short,

        @JsonProperty("childCity")
        var childCity: String,

        @JsonProperty("accountChildId")
        var accountChildId: Account,

        @JsonProperty( "childQuestUUID")
        val childQuestUUID: String?
) {

    constructor(childName: String,childSurname: String,childYearBirth: Short,childCity: String,accountChildId: Account,childQuestUUID: String)
            :this(0,childName,childSurname,childYearBirth,childCity,accountChildId,childQuestUUID)

    override fun toString(): String {
        return "Child(childId=$childId, childName='$childName', childSurname='$childSurname', childYearBirth=$childYearBirth," +
                " childCity='$childCity', accountChildId=$accountChildId, childQuestUUID='$childQuestUUID')"
    }


}