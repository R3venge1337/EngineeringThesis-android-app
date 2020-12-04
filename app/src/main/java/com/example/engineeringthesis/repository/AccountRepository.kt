package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.AccountDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Account
import com.example.engineeringthesis.model.Token
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class AccountRepository @Inject constructor(application: Application?){
    private val accountDAO: AccountDAO
    private var retrofitClient: Retrofit?
    var setToken : Token? = null

    init {
        retrofitClient= RetrofitClient.retrofit
        accountDAO = retrofitClient!!.create(AccountDAO::class.java)
    }

    fun allAccounts(): LiveData<List<Account>> {
        return LiveDataReactiveStreams.fromPublisher(accountDAO.allAccounts().subscribeOn(newThread()))
    }

    fun saveAccount(account:Account)
    {
        accountDAO.saveAccount(account).subscribeOn(newThread()).blockingAwait()
    }

    fun getAccountByName(accountName : String):Account
    {
        return  accountDAO.getAccountByName(accountName).subscribeOn(newThread()).blockingGet()
    }

    fun authenticate(username: String, password:String)
    {
        accountDAO.authenticate(username,password).subscribeOn(newThread()).blockingAwait()
    }

    fun getToken(): Token
    {
        return accountDAO.getToken().subscribeOn(newThread()).blockingGet()
    }

    fun updateAccount(account:Account,accountId:Int)
    {
        return accountDAO.updateAccount(account,accountId).subscribeOn(newThread()).blockingAwait()
    }


}