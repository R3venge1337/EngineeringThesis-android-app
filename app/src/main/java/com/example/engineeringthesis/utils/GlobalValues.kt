package com.example.engineeringthesis.utils

import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Child
import com.example.engineeringthesis.model.Teacher

object GlobalValues {
    var maxAppAge:Int = 4
    var currentAccount: Account? = null
    var currentChild: Child? = null
    var currentTeacher: Teacher? = null
    var JwtToken:String? = ""

    var pageNumberFOP:Int = 0
    var sizeFOP:Int = 4

    var pageNumberFOV:Int = 0
    var sizeFOV:Int = 4

    var pageNumberDAD:Int = 0
    var sizeDAD:Int = 4

    var pageNumberMGA:Int = 0
    var sizeMGA:Int = 8

    var pageNumberSAA:Int = 0
    var sizeSAA:Int = 8


}