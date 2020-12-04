package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime

@Entity(tableName = "account")
data class Account(

        @JsonProperty("accountId")
        @PrimaryKey(autoGenerate = true)
        var accountId: Int,

        @JsonProperty("accountName")
        var accountName: String,

        @JsonProperty("accountPassword")
        var accountPassword: String,

        @JsonProperty("accountCreatedDate")
        @JsonSerialize(using = LocalDateTimeSerializer::class)
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX")
        var  accountCreatedDate: LocalDateTime,

        @JsonProperty("accountEmail")
        var accountEmail: String,

        @JsonProperty("role")
        var role: Role
) {
        constructor(accountName:String,accountPassword:String,accountCreatedDate:LocalDateTime,accountEmail:String,role:Role)
                : this(0,accountName,accountPassword,accountCreatedDate,accountEmail,role)

        override fun toString(): String {
                return "Account(accountId=$accountId, " +
                        "accountName='$accountName', accountPassword='$accountPassword', " +
                        "accountCreatedDate=$accountCreatedDate, accountEmail='$accountEmail', role=$role)"
        }


}