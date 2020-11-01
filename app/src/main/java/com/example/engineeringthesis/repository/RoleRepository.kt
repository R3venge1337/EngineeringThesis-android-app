package com.example.engineeringthesis.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.engineeringthesis.dao.RoleDAO
import com.example.engineeringthesis.database.Retrofit.RetrofitClient
import com.example.engineeringthesis.model.Role
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.newThread

import retrofit2.Retrofit
import javax.inject.Inject

class RoleRepository @Inject constructor(application: Application?) {
    private val roleDAO: RoleDAO
    private var retrofitClient: Retrofit?

    init {
        retrofitClient= RetrofitClient.retrofit
        roleDAO = retrofitClient!!.create(RoleDAO::class.java)
    }

    fun allRoles(): LiveData<List<Role>> {
        return  LiveDataReactiveStreams.fromPublisher(roleDAO.allRoles().subscribeOn(newThread()))
    }

    fun  getRoleByName(roleName:String): Single<Role>
    {
        return roleDAO.getRoleByName(roleName).subscribeOn(newThread())
    }
}