package com.example.engineeringthesis.di.module

import com.example.engineeringthesis.*
import com.example.engineeringthesis.utils.UserNameDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun  bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun  bindCategoryActivity(): CategoryActivity

    @ContributesAndroidInjector
    abstract fun bindLanguageActivity(): LanguageActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindChildRegisterActivity(): ChildRegisterActivity

    @ContributesAndroidInjector
    abstract fun bindTeacherdRegisterActivity(): TeacherRegisterActivity

    @ContributesAndroidInjector
    abstract fun  bindGameActivity(): GameActivity

    @ContributesAndroidInjector
    abstract fun  bindUserNameDialog(): UserNameDialog

    @ContributesAndroidInjector
    abstract fun  bindTeacherSelectionActivity(): TeacherSelectionActivity

    @ContributesAndroidInjector
    abstract fun  bindFindOutPictureActivity(): FindOutPictureActivity

    @ContributesAndroidInjector
    abstract fun  bindChildMyAccountActivity(): ChildMyAccountActivity


}