package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Token
import com.example.engineeringthesis.repository.AccountRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayInputStream


class AccountViewModel(application: Application) : AndroidViewModel(application){
    private val accountRepository: AccountRepository

    init {
        accountRepository = AccountRepository(application)
    }

    fun saveAccount(account: Account)
    {
       accountRepository.saveAccount(account)
    }

    fun getAccountByName(accountName : String): Account
    {
        return accountRepository.getAccountByName(accountName)
    }

    fun authenticate(username :String, password:String)
    {
        accountRepository.authenticate(username, password)
    }

    fun getToken(): Token
    {
       return  accountRepository.getToken()
    }
}