package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

@Entity(tableName = "role")
data class Role(

        @JsonProperty("roleId")
        @PrimaryKey(autoGenerate = true)
        var roleId: Int,

        @JsonProperty("roleName")
        var roleName: String,

        @JsonProperty("roleCreatedDate")
        @JsonSerialize(using = LocalDateTimeSerializer::class)
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
       // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX")
        var  roleCreatedDate: LocalDateTime,
){
    constructor(roleName: String,roleCreatedDate: LocalDateTime)
         :this(0,roleName,roleCreatedDate)

    override fun toString(): String {
        return "Role(roleId=$roleId, roleName='$roleName', roleCreatedDate=$roleCreatedDate)"
    }
}