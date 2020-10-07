package com.example.engineeringthesis.di.module

import android.app.Application
import android.content.Context
import com.example.engineeringthesis.di.ForApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract  class AppModule
{
    @Binds
    @Singleton
    @ForApplication
    abstract fun  provideContext (application: Application) : Context
}