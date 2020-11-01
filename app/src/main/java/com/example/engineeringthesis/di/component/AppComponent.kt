package com.example.engineeringthesis.di.component

import android.app.Application
import com.example.engineeringthesis.MyApp
import com.example.engineeringthesis.di.module.ActivityBindingModule
import com.example.engineeringthesis.di.module.AppModule
import com.example.engineeringthesis.di.module.UserNameDialogListenerModule
import com.example.engineeringthesis.utils.JwtAuthenticationInterceptor
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class,
    UserNameDialogListenerModule::class,JwtAuthenticationInterceptor::class])
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder
        fun build(): AppComponent
    }
}

