package com.example.engineeringthesis

import com.example.engineeringthesis.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


 class MyApp : DaggerApplication() {

     override fun applicationInjector(): AndroidInjector<out MyApp> {
        return DaggerAppComponent.builder().app(this).build()
    }
}

