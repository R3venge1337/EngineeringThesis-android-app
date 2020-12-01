package com.example.engineeringthesis.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.text.ParseException
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class OffsetDateTimeDesSerializer : JsonDeserializer<OffsetDateTime>(){

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jsonparser: JsonParser, context: DeserializationContext?): OffsetDateTime? {
        val date = jsonparser.text
        return try {
            OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } catch (e: ParseException) {
            throw RuntimeException(e)
        }
    }
}
