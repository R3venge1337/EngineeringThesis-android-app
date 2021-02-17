package com.example.engineeringthesis.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Token
import com.example.engineeringthesis.repository.AccountRepository


class AccountViewModel(application: Application) : AndroidViewModel(application){
    private val accountRepository: AccountRepository

    init {
        accountRepository = AccountRepository(application)
    }

    fun saveAccount(account: Account)
    {
       accountRepository.saveAccount(account)
    }

    fun getAllExpiresAccounts(accountExpiredAge : Int): LiveData<List<Account>>
    {
        return accountRepository.allExpiredAccounts(accountExpiredAge)
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

    fun updateAccount(accountId:Int,account: Account)
    {
        accountRepository.updateAccount(accountId,account)
    }
    fun deleteAccount(accountId : Int)
    {
        accountRepository.deleteAccount(accountId)
    }
}