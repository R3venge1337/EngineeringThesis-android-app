package com.example.engineeringthesis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "teacher")
data class Teacher(

        @JsonProperty("teacherId")
        @PrimaryKey(autoGenerate = true)
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

        @JsonProperty("languageTeacherId")
        var languageTeacherId: Language,

        @JsonProperty("accountTeacherId")
        var accountTeacherId: Account
){

        constructor(teacherName:String,teacherSurname:String,teacherYearBirth:Short,teacherCity:String
                    ,teacherProfession:String, teacherLanguage: Language,teacherAccount: Account)
        : this(0,teacherName,teacherSurname,teacherYearBirth,teacherCity,teacherProfession,teacherLanguage,teacherAccount)

        override fun toString(): String {
                return "Teacher(teacherId=$teacherId, teacherName='$teacherName', teacherSurname='$teacherSurname', " +
                        "teacherYearBirth=$teacherYearBirth, teacherCity='$teacherCity', teacherProfession='$teacherProfession'," +
                        "languageTeacherId=$languageTeacherId, accountTeacherId=$accountTeacherId)"
        }
}