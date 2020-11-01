package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "teacher")
data class Teacher(

        @JsonProperty("teacherId")
        @PrimaryKey(autoGenerate = true)
        @JsonIgnore
        var teacherId: Int,

        @JsonProperty("teacherName")
        var teacherName: String,

        @JsonProperty("teacherSurname")
        var teacherSurname: String,

        @JsonProperty("teacherYearBirth")
        var teacherYearBirth: Short,

        @JsonProperty("teacherCity")
        var teacherCity: String,

        @JsonProperty("teacherProfession")
        var teacherProfession: String,

        @JsonProperty("teacherAddress")
        var teacherAddress: String,

        @JsonProperty("teacherZipCode")
        var teacherZipCode: String,

        @JsonProperty("languageTeacherId")
        var languageTeacherId: Language,

        @JsonProperty("accountTeacherId")
        var accountTeacherId: Account
){

        constructor(teacherName:String,teacherSurname:String,teacherYearBirth:Short,teacherCity: String,teacherProfession:String,
                    teacherAddress:String,teacherZipCode:String,teacherLanguage: Language,teacherAccount: Account)
        : this(0,teacherName,teacherSurname,teacherYearBirth,teacherCity,teacherProfession,teacherAddress,teacherZipCode,teacherLanguage,teacherAccount)

        override fun toString(): String {
                return "Teacher(teacherId=$teacherId, teacherName='$teacherName', teacherSurname='$teacherSurname', " +
                        "teacherYearBirth=$teacherYearBirth, teacherCity='$teacherCity', teacherProfession='$teacherProfession'," +
                        " teacherAddress='$teacherAddress', teacherZipCode='$teacherZipCode'," +
                        " languageTeacherId=$languageTeacherId, accountTeacherId=$accountTeacherId)"
        }
}