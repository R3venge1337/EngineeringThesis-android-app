package com.example.engineeringthesis.di.module

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JacksonObjectMapperModule : ObjectMapper() {

    @Provides
    @Singleton
    fun provideObjectMapper() : ObjectMapper
    {
        return ObjectMapper()
    }

}