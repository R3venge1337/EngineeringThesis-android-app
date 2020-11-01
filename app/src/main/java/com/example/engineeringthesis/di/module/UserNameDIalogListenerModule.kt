package com.example.engineeringthesis.di.module

import com.example.engineeringthesis.MainActivity
import com.example.engineeringthesis.utils.UserNameDialog
import dagger.Binds
import dagger.Module

@Module
abstract class UserNameDialogListenerModule {

    @Binds
   abstract fun bindsUserNameDialogListener(mainActivity: MainActivity ) : UserNameDialog.UserNameDialogListener
}