package com.example.engineeringthesis.utils

data class LiveDataErrorHandling<T>(val data: T? = null,
                                 val states: Throwable? = null) {
}