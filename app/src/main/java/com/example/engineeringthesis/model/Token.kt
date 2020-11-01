package com.example.engineeringthesis.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Token(
        @JsonProperty("token")
        val  jwttoken: String
) {
        override fun toString(): String {
                return jwttoken
        }
}