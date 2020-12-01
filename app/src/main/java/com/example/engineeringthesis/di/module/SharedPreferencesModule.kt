package com.example.engineeringthesis.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesEditor(context: Context) : SharedPreferences.Editor
    {
        var editor: SharedPreferences.Editor = context.getSharedPreferences("com.example.engineeringthesis_preferences", Context.MODE_PRIVATE).edit()
        return editor
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences
    {
        var shared: SharedPreferences = context.getSharedPreferences("com.example.engineeringthesis_preferences", Context.MODE_PRIVATE)
        return shared
    }

}