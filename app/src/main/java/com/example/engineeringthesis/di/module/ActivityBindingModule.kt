package com.example.engineeringthesis.di.module

import com.example.engineeringthesis.CategoryActivity
import com.example.engineeringthesis.GameActivity
import com.example.engineeringthesis.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun  bindMainActivity(): MainActivity;

    @ContributesAndroidInjector
    abstract fun  bindCategoryActivity(): CategoryActivity;

    @ContributesAndroidInjector
    abstract fun  bindGameActivity(): GameActivity;
}