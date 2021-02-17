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
    abstract fun  bindFindOutVocabularyActivity(): FindOutVocabularyActivity

    @ContributesAndroidInjector
    abstract fun  bindDragAndDropActivity(): DragAndDropActivity

    @ContributesAndroidInjector
    abstract fun  bindMemoryGameActivity(): MemoryGameActivity

    @ContributesAndroidInjector
    abstract fun  bindSelectAndAdjust(): SelectAndAdjustActivity

    @ContributesAndroidInjector
    abstract fun  bindChildMyAccountActivity(): ChildMyAccountActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherMyAccountActivity(): TeacherMyAccountActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherAddResourcesActivity():TeacherAddResourcesActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherAddCategoryActivity():TeacherAddCategoryActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherAddWordActivity():TeacherAddWordActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherAddImageActivity():TeacherAddImageActivity

    @ContributesAndroidInjector
    abstract fun  bindTeacherAddAudioActivity():TeacherAddAudioActivity

    @ContributesAndroidInjector
    abstract fun  bindWinViewActivity(): WinViewActivity

    @ContributesAndroidInjector
    abstract fun  bindAdminDeleteAccountsActivity():  AdminDeleteAccountsActivity

    @ContributesAndroidInjector
    abstract fun  bindChildResultsActivity(): ChildResultsActivity

    @ContributesAndroidInjector
    abstract fun  bindChildButtRankActivity(): ChildButtRankActivity

    @ContributesAndroidInjector
    abstract fun  bindChildRankGameActivity(): ChildRankGameActivity


}