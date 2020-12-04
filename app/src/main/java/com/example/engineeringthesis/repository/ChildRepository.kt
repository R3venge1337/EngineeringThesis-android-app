package com.example.engineeringthesis.repository

import android.app.Application
import com.example.engineeringthesis.dao.ChildDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Child
import io.reactivex.schedulers.Schedulers.newThread
import retrofit2.Retrofit
import javax.inject.Inject

class ChildRepository @Inject constructor(application: Application?) {
    private val childDAO: ChildDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        childDAO = retrofitClient!!.create(ChildDAO::class.java)
    }

    fun saveChild(child : Child)
    {
        childDAO.saveChild(child).subscribeOn(newThread()).blockingAwait()
    }

    fun getChildWithAccountDetails(accountName:String) : Child
    {
      return  childDAO.getChildWithAccount(accountName).subscribeOn(newThread()).blockingGet()
    }

    fun updateChild(child : Child,childId : Int)
    {
        childDAO.updateChild(child,childId).subscribeOn(newThread()).blockingAwait()
    }

}