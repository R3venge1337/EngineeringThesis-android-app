package com.example.engineeringthesis.model

import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryTeacher(

        @JsonProperty("category_teacher_id_pk")
        @PrimaryKey(autoGenerate = true)
        var categoryTeacherId:Int,

        @JsonProperty("categoryId")
        var categoryId:Category,

        @JsonProperty("teacherId")
        var  teacherId: Teacher,

        @JsonProperty("isNew")
        var isNew: Boolean,

        @JsonProperty("isAccepted")
        var isAccepted: Boolean
) {
    constructor(categoryId:Category,teacherId:Teacher,isNew:Boolean,isAccepted:Boolean):
           this(0,categoryId,teacherId,isNew,isAccepted)

    override fun toString(): String {
        return "categoryTeacherId=$categoryTeacherId, categoryId=$categoryId, teacherId=$teacherId, isNew=$isNew, isAccepted=$isAccepted"
    }
}
